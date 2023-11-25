package com.challeng.dto;

import com.challeng.domain.VotoEnum;

public record VotoDTO(
        Long sessaoVotacaoID,

        Long associadoId,

        VotoEnum voto) {
}
