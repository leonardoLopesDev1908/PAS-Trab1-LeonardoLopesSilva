package com.acmepolling.acme.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 
public class Localidade{

    @Id 
    @GeneratedValue(strategy=GenerationType.UUID)
    UUID id;

    @Column
    private String cep;

    @Column
    private String cidade;

    @Column
    private String estado;

    @OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Candidato> candidatos = new ArrayList<>();
}