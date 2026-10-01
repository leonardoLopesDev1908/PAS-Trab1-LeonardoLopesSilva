package com.acmepolling.acme.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID String;

    @Column
    private int codigo;

    @Column
    private String nome;

    @Column
    private int anoFundacao;

    @OneToMany(mappedBy = "partido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Candidato> candidatos = new ArrayList<>();
}