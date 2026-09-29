package com.acmepolling.acme.entity;

import java.util.UUID;

import com.acmepolling.acme.entity.enums.CandidatoStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    private CandidatoStatus status;

    @Column
    private String partido;
    
    //@Column
    //private Partido partido;

    // Na entidade Candidato (ou na classe onde está o campo)
    @ManyToOne
    @JoinColumn(name = "localidade_id")
    private Localidade localidade;
}