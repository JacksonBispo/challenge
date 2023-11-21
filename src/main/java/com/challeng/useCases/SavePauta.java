package com.challeng.useCases;

import com.challeng.domain.Pauta;
import com.challeng.dto.PautaDTO;
import com.challeng.repository.PautaRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SavePauta {

    private final PautaRespository repository;

    public Pauta execute(PautaDTO pautaDTO){
        var pauta = new Pauta(
                pautaDTO.id(),
                pautaDTO.descricao()
        );
        return  repository.save(pauta);
    }
}
