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

    public Sessao execute(Long pautaId, Integer duracaoMinutos) {
        log.info("search pauta with id: {} ", pautaId);
        var pauta = pautaRepository.findById(pautaId)
                .orElseThrow(() -> new EntityNotFoundException("Pauta não encontrada com o ID: " + pautaId));
        var start = LocalDateTime.now();
        var end = start.plusMinutes(duracaoMinutos);

        var sessaoDTO = new SessaoDTO(
                null,
                pauta.getId(),
                start,
                end
        );
        var entity = new Sessao(
                sessaoDTO.id(),
                pauta,
                sessaoDTO.start(),
                sessaoDTO.end()
        );
        log.info("saving a new Session Vote..");
        return sessaoRepository.save(entity);
    }
}
