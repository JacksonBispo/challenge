package com.challeng.useCases;

import com.challeng.domain.VotoEnum;
import com.challeng.dto.ResultadoVotacaoDTO;
import com.challeng.exception.EntityNotFoundException;
import com.challeng.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContabilizarVotos {

    private final PautaRepository pautaRepository;

    public ResultadoVotacaoDTO execute(Long pautaId){
        long totalVotos ;
         var pauta  = pautaRepository.findById(pautaId)
                 .orElseThrow(() -> new EntityNotFoundException("Pauta não encontrada com o ID: " + pautaId));
        ;
        long votosSim = pauta.getVotos().stream()
                .filter(voto -> voto.getVoto() == VotoEnum.SIM)
                .count();

        long votosNao = pauta.getVotos().stream()
                .filter(voto -> voto.getVoto() == VotoEnum.NAO)
                .count();

        return new ResultadoVotacaoDTO(pauta.getVotos().size(), votosSim, votosNao);
    }
}
