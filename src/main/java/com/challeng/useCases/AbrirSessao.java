package com.challeng.useCases;

import com.challeng.domain.Pauta;
import com.challeng.domain.Sessao;
import com.challeng.dto.PautaDTO;
import com.challeng.dto.SessaoDTO;
import com.challeng.exception.EntityNotFoundException;
import com.challeng.repository.PautaRepository;
import com.challeng.repository.SessaoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AbrirSessao {

    private final PautaRepository pautaRepository;

    private final SessaoRepository sessaoRepository;

    public SessaoDTO execute(Long pautaId, Integer duracaoMinutos) {
        log.info("search pauta with id: {} ", pautaId);
                var pauta = pautaRepository.findById(pautaId)
                .orElseThrow(() -> new EntityNotFoundException("Pauta não encontrada com o ID: " + pautaId));
        var start = LocalDateTime.now();
        var end = start.plusMinutes(duracaoMinutos);


        var entity = new Sessao();
                entity.setPauta(pauta);
                entity.setInicio(start);
                entity.setFim(end);
        log.info("saving a new Session Vote..");
        var sessaoSaved = sessaoRepository.save(entity);
        return new SessaoDTO(
                sessaoSaved.getId(),
                sessaoSaved.getPauta().getId(),
                sessaoSaved.getInicio(),
                sessaoSaved.getFim()
        );

    }
}
