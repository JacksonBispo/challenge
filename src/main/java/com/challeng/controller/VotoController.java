package com.challeng.controller;

import com.challeng.domain.Associado;
import com.challeng.domain.Sessao;
import com.challeng.domain.Voto;
import com.challeng.dto.VotoDTO;
import com.challeng.useCases.RegistrarVotos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/votos")
public class VotoController {


    @Autowired
    private RegistrarVotos votoService;

    @PostMapping("/registrar")
    public ResponseEntity<VotoDTO> registrarVoto(@RequestBody VotoDTO votoDTO) {
        var savedVoto = votoService.execute(votoDTO);
        var voto = new VotoDTO(savedVoto.getId(), savedVoto.getAssociado().getId(),
                savedVoto.getVoto());
        return new ResponseEntity<>(voto, HttpStatus.CREATED);
    }
}
