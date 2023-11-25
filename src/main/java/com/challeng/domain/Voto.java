package com.challeng.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_voto")
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sessao_id")
    private Sessao sessaoVotacao;

    @OneToOne
    @JoinColumn(name = "associado_id")
    private Associado associado;

    @Enumerated(value = EnumType.ORDINAL)
    private VotoEnum voto;

}
