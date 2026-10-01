package com.acmepolling.acme.config;

import java.util.ArrayList;
import java.util.UUID;

import com.acmepolling.acme.entity.Partido;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cglib.core.Local;
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
            Localidade local1 = Localidade.builder()
                    .cidade("Porto Alegre")
                    .estado("Rio Grande do Sul")
                    .cep("12314215")
                    .build();

            Localidade local2 = Localidade.builder()
                    .cidade("Canoas")
                    .estado("Rio Grande do Sul")
                    .cep("12314217")
                    .build();

            Localidade local3 = Localidade.builder()
                    .cidade("Cachoeirinha")
                    .estado("Rio Grande do Sul")
                    .cep("12314219")
                    .build();

            Partido partido1 = Partido.builder()
                    .nome("Partido Político Partidário")
                    .anoFundacao(2017)
                    .candidatos(new ArrayList<>())
                    .build();

            Partido partido2 = Partido.builder()
                    .nome("Partido da Política Brasileira")
                    .anoFundacao(1994)
                    .candidatos(new ArrayList<>())
                    .build();

            Partido partido3 = Partido.builder()
                    .nome("Partido Colunista Brasileiro")
                    .anoFundacao(1922)
                    .candidatos(new ArrayList<>())
                    .build();

            Candidato candidato = Candidato.builder()
                .nome("Candidato Teste 1")
                .status(CandidatoStatus.PRE_CANDIDATO)
                .partido(partido1)
                .localidade(local1)
                .build();

            repository.save(candidato);

            Candidato candidato2 = Candidato.builder()
                .nome("Candidato Teste 2")  
                .status(CandidatoStatus.PRE_CANDIDATO)
                .partido(partido2)
                .localidade(local1)
                .build();

            repository.save(candidato2);

            Candidato candidato3 = Candidato.builder()
                .nome("Candidato Teste 3")
                .status(CandidatoStatus.PRE_CANDIDATO)
                .partido(partido3)
                .localidade(local3)
                .build();

            repository.save(candidato3);
        };
    }
}