package com.challeng.dto;

import com.challeng.domain.VotoEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record PautaDTO(
        Long id,
        @NotEmpty @Size(max = 255, min = 10)
        String descricao) {
}
