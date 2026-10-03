package com.acmepolling.acme.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime hora;

    @Column
    private Boolean isValid;

    @ManyToOne
    @JoinColumn(name = "localidade_id")
    private Localidade localidade;

    @ManyToOne(fetch = FetchType.LAZY)
    private Candidato candidato;
}