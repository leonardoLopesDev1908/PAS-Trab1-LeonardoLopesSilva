package com.acmepolling.acme.config;

import java.util.ArrayList;
import java.util.UUID;

import com.acmepolling.acme.entity.Partido;
import com.acmepolling.acme.entity.Voto;
import com.acmepolling.acme.external.CepApi;
import com.acmepolling.acme.repository.LocalidadeRepository;
import com.acmepolling.acme.repository.PartidoRepository;
import com.acmepolling.acme.service.voto.VotacaoService;
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
    private final VotacaoService votacaoService;

    public Seed(CandidatoRepository candidatoRepository, LocalidadeRepository localidadeRepository,
                PartidoRepository partidoRepository, VotacaoService votacaoService) {
        this.candidatoRepository = candidatoRepository;
        this.localidadeRepository = localidadeRepository;
        this.partidoRepository = partidoRepository;
        this.votacaoService = votacaoService;
    }
//
//    @Bean
//    CommandLineRunner basicInitDatabase(CandidatoRepository repository) {
//        return args -> {
//            Localidade local1 = CepApi.getLocalidade("94935050");
//
//            Localidade local2 = CepApi.getLocalidade("94970530");
//
//            Localidade local3 = CepApi.getLocalidade("90010170");
//
//            localidadeRepository.save(local1);
//            localidadeRepository.save(local2);
//            localidadeRepository.save(local3);
//
//            Partido partido1 = Partido.builder()
//                    .nome("Partido Político Partidário")
//                    .codigo("60")
//                    .anoFundacao(2017)
//                    .candidatos(new ArrayList<>())
//                    .build();
//
//            Partido partido2 = Partido.builder()
//                    .nome("Partido da Política Brasileira")
//                    .codigo("99")
//                    .anoFundacao(1994)
//                    .candidatos(new ArrayList<>())
//                    .build();
//
//            Partido partido3 = Partido.builder()
//                    .nome("Partido Colunista Brasileiro")
//                    .codigo("11")
//                    .anoFundacao(1922)
//                    .candidatos(new ArrayList<>())
//                    .build();
//            partidoRepository.save(partido1);
//            partidoRepository.save(partido2);
//            partidoRepository.save(partido3);
//
//            Candidato candidato = Candidato.builder()
//                .nome("Candidato Teste 1")
//                .codigo(partido1.getCodigo())
//                .status(CandidatoStatus.PRE_CANDIDATO)
//                .partido(partido1)
//                .localidade(local1)
//                .build();
//
//            repository.save(candidato);
//
//            Candidato candidato2 = Candidato.builder()
//                .nome("Candidato Teste 2")
//                .codigo(partido2.getCodigo())
//                .status(CandidatoStatus.PRE_CANDIDATO)
//                .partido(partido2)
//                .localidade(local2)
//                .build();
//
//            repository.save(candidato2);
//
//            Candidato candidato3 = Candidato.builder()
//                .nome("Candidato Teste 3")
//                .codigo(partido3.getCodigo())
//                .status(CandidatoStatus.PRE_CANDIDATO)
//                .partido(partido3)
//                .localidade(local3)
//                .build();
//
//            repository.save(candidato3);
//        };
//    }

    @Bean
    CommandLineRunner completeInitDatabase(CandidatoRepository repository) {
 return args -> {
            Localidade local1 = CepApi.getLocalidade("94935050");

            Localidade local2 = CepApi.getLocalidade("94970530");

            Localidade local3 = CepApi.getLocalidade("90010170");

            localidadeRepository.save(local1);
            localidadeRepository.save(local2);
            localidadeRepository.save(local3);

            Partido partido1 = Partido.builder()
                    .nome("Partido Político Partidário")
                    .codigo("60")
                    .anoFundacao(2017)
                    .candidatos(new ArrayList<>())
                    .build();

            Partido partido2 = Partido.builder()
                    .nome("Partido da Política Brasileira")
                    .codigo("99")
                    .anoFundacao(1994)
                    .candidatos(new ArrayList<>())
                    .build();

            Partido partido3 = Partido.builder()
                    .nome("Partido Colunista Brasileiro")
                    .codigo("11")
                    .anoFundacao(1922)
                    .candidatos(new ArrayList<>())
                    .build();
            partidoRepository.save(partido1);
            partidoRepository.save(partido2);
            partidoRepository.save(partido3);

            Candidato candidato = Candidato.builder()
                .nome("Candidato Teste 1")
                .codigo(partido1.getCodigo())
                .status(CandidatoStatus.PRE_CANDIDATO)
                .partido(partido1)
                .localidade(local1)
                .build();

            repository.save(candidato);

            Candidato candidato2 = Candidato.builder()
                .nome("Candidato Teste 2")
                .codigo(partido2.getCodigo())
                .status(CandidatoStatus.PRE_CANDIDATO)
                .partido(partido2)
                .localidade(local2)
                .build();

            repository.save(candidato2);

            Candidato candidato3 = Candidato.builder()
                .nome("Candidato Teste 3")
                .codigo(partido3.getCodigo())
                .status(CandidatoStatus.PRE_CANDIDATO)
                .partido(partido3)
                .localidade(local3)
                .build();

            repository.save(candidato3);

            votacaoService.cadVoto("94970530", "99");
            votacaoService.cadVoto("94970530", "99");
            votacaoService.cadVoto("90010170", "11");
            votacaoService.cadVoto("94970530", "99");
            votacaoService.cadVoto("94935050", "60");
            votacaoService.cadVoto("90010170", "11");
            votacaoService.cadVoto("94970530", "99");
        };
    }
}