package com.acmepolling.acme.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
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

    @Column
    private int numMembros;

    @OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Candidato> candidatos = new ArrayList<>();
}