package com.challeng.controller;

import com.challeng.dto.SessaoDTO;
import com.challeng.useCases.AbrirSessao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sessao")
public class SessaoController {

    private final AbrirSessao abrirSessao;

    @PostMapping(value = {"/abrir/{pautaId}","/abrir/{pautaId}/{duracaoMinutos}"})
    public ResponseEntity<SessaoDTO> abrir(@PathVariable Long pautaId, @PathVariable(required = false) Integer duracaoMinutos,
                                           UriComponentsBuilder uriBuilder){
        var sessao = abrirSessao.execute(pautaId, duracaoMinutos);
        var uri = uriBuilder.path("/abrir/{id}").buildAndExpand(sessao.id()).toUri();
        return ResponseEntity.created(uri).body(sessao);
    }

}
