package com.challeng.controller;

import com.challeng.dto.AssociadoDTO;
import com.challeng.useCases.SaveAssociado;
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
@RequestMapping("/api/v1/associado")
public class AssociadoController {

    private final SaveAssociado saveAssociado;

    @PostMapping("/save")
    public ResponseEntity<AssociadoDTO> save(@RequestBody @Valid AssociadoDTO associadoDTO, UriComponentsBuilder uriBuilder){
            var associado =  saveAssociado.execute(associadoDTO);
            var uri = uriBuilder.path("associado/{id}").buildAndExpand(associado.id()).toUri();
        return ResponseEntity.created(uri).body(associado);
    }
}
