package com.challeng.useCases;

import com.challeng.domain.Voto;
import com.challeng.domain.VotoEnum;
import com.challeng.exception.EntityNotFoundException;
import com.challeng.repository.SessaoRepository;
import com.challeng.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrarVotos {

    private final VotoRepository votoRepository;

    private final SessaoRepository sessaoRepository;

    public Voto execute(Voto voto) {
        var sessaoVotacao = sessaoRepository.findById(voto.getSessaoVotacao().getId())
                .orElseThrow(() -> new EntityNotFoundException("Sessão de votação não encontrada com o ID: " + voto.getSessaoVotacao().getId()));
        if (associadoJaVotou(voto.getSessaoVotacao().getId(), voto.getAssociado().getId())) {
            throw new IllegalStateException("Associado já votou nesta sessão de votação.");
        }
        if(!sessaoVotacao.opened()) {
            throw new IllegalStateException("Sessao esta fechada!");
        }
        return votoRepository.save(voto);
    }

    private boolean associadoJaVotou(Long sessaoVotacaoId, Long associadoId) {
        return votoRepository.existsBySessaoVotacaoIdAndAssociadoId(sessaoVotacaoId, associadoId);
    }
}
