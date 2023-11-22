package com.challeng.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PautaDTO(
        Long id,
        @NotEmpty @Size(max = 255, min = 10)
        String descricao) {
}
