package com.acmepolling.acme.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.acmepolling.acme.entity.enums.CandidatoStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor 
@Entity
@Table()
public class Candidato {

    @Id 
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    @Column
    private String nome;

    @Column
    private String codigo;

    @Column
    private CandidatoStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "candidato_id")
    private Partido partido;
    
    // Na entidade Candidato (ou na classe onde está o campo)
    @ManyToOne
    @JoinColumn(name = "localidade_id")
    private Localidade localidade;

    @ManyToMany(mappedBy = "candidatos")
    private Set<Voto> votos = new HashSet<>();
}