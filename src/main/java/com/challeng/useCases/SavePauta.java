package com.challeng.useCases;

import com.challeng.domain.Pauta;
import com.challeng.dto.PautaDTO;
import com.challeng.repository.PautaRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SavePauta {

    private final PautaRepository repository;

    public PautaDTO execute(@Valid  PautaDTO pautaDTO){
        log.info("Instance of a new Pauta");
        var pauta = new Pauta(
                pautaDTO.id(),
                pautaDTO.descricao(),
                null
        );
        log.info("Save the new Pauta");
        var pautaSaved =   repository.save(pauta);

        return new PautaDTO(pautaSaved.getId(), pautaSaved.getDescricao());
    }
}
