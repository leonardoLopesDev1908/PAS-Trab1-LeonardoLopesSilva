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

    @ManyToOne
    @JoinColumn(name = "localidade_id")
    private Localidade localidade;

    @ManyToMany
    @JoinTable(
            name = "tb_voto_candidato",
            joinColumns = @JoinColumn(name = "voto_id"),
            inverseJoinColumns = @JoinColumn(name = "candidato_id")
    )
    private List<Candidato> candidatos = new ArrayList<>();
}