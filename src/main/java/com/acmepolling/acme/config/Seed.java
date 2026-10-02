package com.acmepolling.acme.config;

import java.util.ArrayList;
import java.util.UUID;

import com.acmepolling.acme.entity.Partido;
import com.acmepolling.acme.repository.LocalidadeRepository;
import com.acmepolling.acme.repository.PartidoRepository;
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

    private final CandidatoRepository candidatoRepository;
    private final LocalidadeRepository localidadeRepository;
    private final PartidoRepository partidoRepository;

    public Seed(CandidatoRepository candidatoRepository, LocalidadeRepository localidadeRepository,
                PartidoRepository partidoRepository) {
        this.candidatoRepository = candidatoRepository;
        this.localidadeRepository = localidadeRepository;
        this.partidoRepository = partidoRepository;
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
            localidadeRepository.save(local1);
            localidadeRepository.save(local2);
            localidadeRepository.save(local3);

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
            partidoRepository.save(partido1);
            partidoRepository.save(partido2);
            partidoRepository.save(partido3);

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