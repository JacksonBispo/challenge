package com.challeng.controller;

import com.challeng.dto.ResultadoVotacaoDTO;
import com.challeng.useCases.ContabilizarVotos;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/resultado")
public class ResultadoVotacaoController {

    private final ContabilizarVotos contabilizarVotos;

    @GetMapping("/{pautaId}/resultados")
    public ResponseEntity<ResultadoVotacaoDTO> getResultados(@PathVariable Long pautaId){
        return ResponseEntity.ok(contabilizarVotos.execute(pautaId));
    }


}
