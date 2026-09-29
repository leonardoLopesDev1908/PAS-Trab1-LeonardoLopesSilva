package com.acmepolling.acme.config;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.acmepolling.acme.entity.Candidato;
import com.acmepolling.acme.entity.Localidade;
import com.acmepolling.acme.entity.enums.CandidatoStatus;
import com.acmepolling.acme.repository.CandidatoRepository;

@Configuration
public class Seed {

    private final CandidatoRepository repository;

    public Seed(CandidatoRepository repository) {
        this.repository = repository;
    }

    @Bean
    CommandLineRunner initDatabase(CandidatoRepository repository) {
        return args -> {
            Localidade local1 = new Localidade(UUID.randomUUID(), "Porto Alegre", "Rio Grande do Sul");
            Candidato candidato = Candidato.builder()
                .nome("Candidato Teste 1")
                .status(CandidatoStatus.PRE_CANDIDATO)
                .partido("PTT")
                .localidade(local1)
                .build();

            repository.save(candidato);

            Candidato candidato2 = Candidato.builder()
                .nome("Candidato Teste 2")  
                .status(CandidatoStatus.PRE_CANDIDATO)
                .partido("PYZ")
                .localidade(local1)
                .build();

            repository.save(candidato2);

            Localidade local3 = new Localidade(UUID.randomUUID(), "Gravataí", "Rio Grande do Sul");
            Candidato candidato3 = Candidato.builder()
                .nome("Candidato Teste 3")
                .status(CandidatoStatus.PRE_CANDIDATO)
                .partido("PIX")
                .localidade(local3)
                .build();

            repository.save(candidato3);
        };
    }
}