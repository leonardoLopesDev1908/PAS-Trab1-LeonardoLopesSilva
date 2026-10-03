package com.acmepolling.acme.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.acmepolling.acme.entity.enums.CandidatoStatus;

import jakarta.persistence.*;
import lombok.*;

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

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(255) DEFAULT 'PRE_CANDIDATO'")
    private CandidatoStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "partido_id")
    private Partido partido;
    
    @ManyToOne
    @JoinColumn(name = "localidade_id")
    private Localidade localidade;

    @ManyToMany(mappedBy = "candidato")
    private Set<Voto> votos = new HashSet<>();
}
