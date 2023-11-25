package com.challeng.dto;

import com.challeng.domain.Pauta;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public record SessaoDTO(
         Long id,
         Long pautaId,
         LocalDateTime start,
         LocalDateTime end
) {
}
