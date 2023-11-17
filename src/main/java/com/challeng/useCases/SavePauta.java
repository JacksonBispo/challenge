package com.challeng.useCases;

import com.challeng.domain.Pauta;
import com.challeng.repository.PautaRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SavePauta {

    private final PautaRespository respository;

    public Pauta execute()
}
