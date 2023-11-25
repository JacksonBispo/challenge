package com.challeng.useCases;

import com.challeng.configuration.swagger.FeatureToogleConfig;
import com.challeng.domain.Sessao;
import com.challeng.domain.Voto;
import com.challeng.dto.VotoDTO;
import com.challeng.exception.AssociadoJaVotouException;
import com.challeng.exception.CpfinvalidException;
import com.challeng.exception.EntityNotFoundException;
import com.challeng.exception.SessionCosedException;
import com.challeng.repository.AssociadoRepository;
import com.challeng.repository.SessaoRepository;
import com.challeng.repository.VotoRepository;
import com.challeng.service.CheckingCPF;
import com.challeng.service.KafkaMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrarVotos {

    private final VotoRepository votoRepository;

    private final SessaoRepository sessaoRepository;

    private final AssociadoRepository associadoRepository;

    private final CheckingCPF checkingCPF;

    private final FeatureToogleConfig featureToogleConfig;

    private final KafkaMessageService kafkaMessageService;

    private final ContabilizarVotos contabilizarVotos;

    public Voto execute(VotoDTO votoDTO) {
        var sessaoVotacao = sessaoRepository.findById(votoDTO.sessaoVotacaoID())
                .orElseThrow(() -> new EntityNotFoundException("Sessão de votação não encontrada com o ID: " + votoDTO.sessaoVotacaoID()));

        if (associadoJaVotou(votoDTO.sessaoVotacaoID(), votoDTO.associadoId())) {
            throw new AssociadoJaVotouException("Associado já votou nesta sessão de votação.");
        }

        var associado = associadoRepository.findById(votoDTO.associadoId())
                .orElseThrow(()-> new EntityNotFoundException("Associado nao encontrado"));
        if(featureToogleConfig.getCheckingCpf()) {
            var status = checkingCPF.checkCpf(associado.getCpf());


            if ("UNABLE_TO_VOTE".equals(status.status())) {
                throw new CpfinvalidException("cpf nao valido");
            }
        }

        var voto = new Voto(
                null,
                sessaoVotacao,
                associado,
                votoDTO.voto()
        );

        var updateSessaoVotacao = new Sessao();
        updateSessaoVotacao.setId(votoDTO.sessaoVotacaoID());
        updateSessaoVotacao.setPauta(sessaoVotacao.getPauta());
        updateSessaoVotacao.getPauta().getVotos().add(voto);
        updateSessaoVotacao.setInicio(sessaoVotacao.getInicio());
        updateSessaoVotacao.setFim(sessaoVotacao.getFim());


        sessaoRepository.save(updateSessaoVotacao);
        if(!sessaoVotacao.opened()) {
            var resultado = contabilizarVotos.execute(updateSessaoVotacao.getPauta().getId());
            kafkaMessageService.sendMessage(resultado.toString());
            throw new SessionCosedException("Sessao esta fechada!");
        }


        var votoSaved =   votoRepository.save(voto);


        return votoSaved;

   }

    private boolean associadoJaVotou(Long sessaoVotacaoId, Long associadoId) {
        return votoRepository.existsBySessaoVotacaoIdAndAssociadoId(sessaoVotacaoId, associadoId);
    }
}
