package com.acmepolling.acme.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @OneToMany(mappedBy = "localidade", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Candidato> candidatos = new ArrayList<>();
}