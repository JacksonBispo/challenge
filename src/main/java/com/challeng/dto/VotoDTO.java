package com.challeng.dto;

import com.challeng.domain.VotoEnum;
import jakarta.validation.constraints.NotEmpty;

public record VotoDTO(
        Long sessaoVotacaoID,

        Long associadoId,

        @NotEmpty
        VotoEnum voto) {
}
