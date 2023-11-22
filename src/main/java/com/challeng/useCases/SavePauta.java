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

    public Pauta execute(@Valid  PautaDTO pautaDTO){
        log.info("Instance of a new Pauta");
        var pauta = new Pauta(
                pautaDTO.id(),
                pautaDTO.descricao()
        );
        log.info("Save the new Pauta");
        return  repository.save(pauta);
    }
}
