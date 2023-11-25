package com.challeng.controller;

import com.challeng.dto.PautaDTO;
import com.challeng.useCases.SavePauta;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pauta")
public class PautaController {

    private final SavePauta savePauta;

    @PostMapping("/save")
    public ResponseEntity<PautaDTO> savePauta(@RequestBody @Valid PautaDTO pautaDTO, UriComponentsBuilder uriBuilder){
        var pauta = savePauta.execute(pautaDTO);
        var uri = uriBuilder.path("/pauta/{id}").buildAndExpand(pauta.id()).toUri();
        return ResponseEntity.created(uri).body(pautaDTO);
    }

}
