package com.challeng.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

public record AssociadoDTO(
     Long id,
     String name,

     String cpf
) {
}