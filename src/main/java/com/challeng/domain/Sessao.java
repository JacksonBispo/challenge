package com.challeng.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_sessao")
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    @ManyToOne
    @JoinColumn(name = "voto_id")
    private Voto voto;

    private LocalDateTime inicio = LocalDateTime.now();

    private LocalDateTime fim = inicio.plusMinutes(1);

    public Boolean opened(){
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(this.getInicio()) && now.isBefore(this.getFim());
    }
}
