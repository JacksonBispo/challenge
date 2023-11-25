package com.challeng.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record AssociadoDTO(
     Long id,
     @NotEmpty @Size(max = 255, min = 10)
     String name,

     @CPF
     String cpf
) {
}