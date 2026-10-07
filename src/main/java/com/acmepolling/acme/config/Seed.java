package com.acmepolling.acme.config;

import java.util.ArrayList;

import com.acmepolling.acme.entity.Candidato;
import com.acmepolling.acme.entity.Localidade;
import com.acmepolling.acme.entity.Partido;
import com.acmepolling.acme.entity.enums.CandidatoSituacao;
import com.acmepolling.acme.external.CepApi;
import com.acmepolling.acme.repository.CandidatoRepository;
import com.acmepolling.acme.repository.LocalidadeRepository;
import com.acmepolling.acme.repository.PartidoRepository;
import com.acmepolling.acme.service.voto.VotacaoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    @Bean
    CommandLineRunner completeInitDatabase(CandidatoRepository repository) {
        return args -> {
            Localidade local1 = CepApi.getLocalidade("94935050");
            Localidade local2 = CepApi.getLocalidade("94970530");
            Localidade local3 = CepApi.getLocalidade("90010170");

            local1 = localidadeRepository.saveAndFlush(local1);
            local2 = localidadeRepository.saveAndFlush(local2);
            local3 = localidadeRepository.saveAndFlush(local3);

            Partido partido10 = Partido.builder()
                    .nome("Partido Político Partidário")
                    .numero("10")
                    .anoFundacao(2017)
                    .candidatos(new ArrayList<>())
                    .build();

            Partido partido20 = Partido.builder()
                    .nome("Partido da Política Brasileira")
                    .numero("20")
                    .anoFundacao(1994)
                    .candidatos(new ArrayList<>())
                    .build();

            Partido partido30 = Partido.builder()
                    .nome("Partido Colunista Brasileiro")
                    .numero("30")
                    .anoFundacao(1922)
                    .candidatos(new ArrayList<>())
                    .build();

            Partido partido40 = Partido.builder()
                    .nome("Partido do Tiktok")
                    .numero("40")
                    .anoFundacao(2025)
                    .candidatos(new ArrayList<>())
                    .build();

            Partido partido50 = Partido.builder()
                    .nome("Partido Sem Proposta")
                    .numero("50")
                    .anoFundacao(2005)
                    .candidatos(new ArrayList<>())
                    .build();

            Partido partido60 = Partido.builder()
                    .nome("Partido Atrasado")
                    .numero("60")
                    .anoFundacao(1981)
                    .candidatos(new ArrayList<>())
                    .build();

            partidoRepository.saveAndFlush(partido10);
            partidoRepository.saveAndFlush(partido20);
            partidoRepository.saveAndFlush(partido30);
            partidoRepository.saveAndFlush(partido40);
            partidoRepository.saveAndFlush(partido50);
            partidoRepository.saveAndFlush(partido60);

            // Local 1: Partidos 10 e 30
            Candidato candidato1 = Candidato.builder()
                    .nome("Candidato 1")
                    .numero(partido10.getNumero())
                    .situacao(CandidatoSituacao.ELEGIVEL)
                    .partido(partido10)
                    .localidade(local1)
                    .build();

            Candidato candidato2 = Candidato.builder()
                    .nome("Candidato 2")
                    .numero(partido20.getNumero())
                    .situacao(CandidatoSituacao.ELEGIVEL)
                    .partido(partido20)
                    .localidade(local1)
                    .build();

            repository.saveAndFlush(candidato1);
            repository.saveAndFlush(candidato2);

            // Local 2: Partidos 10 e 20
            Candidato candidato3 = Candidato.builder()
                    .nome("Candidato 3")
                    .numero(partido30.getNumero())
                    .situacao(CandidatoSituacao.ELEGIVEL)
                    .partido(partido30)
                    .localidade(local2)
                    .build();

            Candidato candidato4 = Candidato.builder()
                    .nome("Candidato 4")
                    .numero(partido40.getNumero())
                    .situacao(CandidatoSituacao.ELEGIVEL)
                    .partido(partido40)
                    .localidade(local2)
                    .build();

            repository.saveAndFlush(candidato3);
            repository.saveAndFlush(candidato4);

            // Local 3: Partidos 20 e 30
            Candidato candidato5 = Candidato.builder()
                    .nome("Candidato 5")
                    .numero(partido50.getNumero())
                    .situacao(CandidatoSituacao.ELEGIVEL)
                    .partido(partido50)
                    .localidade(local3)
                    .build();

            Candidato candidato6 = Candidato.builder()
                    .nome("Candidato 6")
                    .numero(partido60.getNumero())
                    .situacao(CandidatoSituacao.ELEGIVEL)
                    .partido(partido60)
                    .localidade(local3)
                    .build();

            repository.saveAndFlush(candidato5);
            repository.saveAndFlush(candidato6);

            // Votos local 1
            votacaoService.cadVoto(local1.getCep(), partido10.getNumero());
            votacaoService.cadVoto(local1.getCep(), partido10.getNumero());
            votacaoService.cadVoto(local1.getCep(), partido10.getNumero());
            votacaoService.cadVoto(local1.getCep(), partido10.getNumero());
            votacaoService.cadVoto(local1.getCep(), partido10.getNumero());
            votacaoService.cadVoto(local1.getCep(), partido20.getNumero());
            votacaoService.cadVoto(local1.getCep(), partido20.getNumero());
            votacaoService.cadVoto(local1.getCep(), partido20.getNumero());
            votacaoService.cadVoto(local1.getCep(), partido20.getNumero());

            // Votos local 2
            votacaoService.cadVoto(local2.getCep(), partido30.getNumero());
            votacaoService.cadVoto(local2.getCep(), partido30.getNumero());
            votacaoService.cadVoto(local2.getCep(), partido30.getNumero());
            votacaoService.cadVoto(local2.getCep(), partido40.getNumero());
            votacaoService.cadVoto(local2.getCep(), partido40.getNumero());
            votacaoService.cadVoto(local2.getCep(), partido40.getNumero());
            votacaoService.cadVoto(local2.getCep(), partido40.getNumero());
            votacaoService.cadVoto(local2.getCep(), partido40.getNumero());
            votacaoService.cadVoto(local2.getCep(), partido40.getNumero());

            // Votos local 3
            votacaoService.cadVoto(local3.getCep(), partido50.getNumero());
            votacaoService.cadVoto(local3.getCep(), partido50.getNumero());
            votacaoService.cadVoto(local3.getCep(), partido50.getNumero());
            votacaoService.cadVoto(local3.getCep(), partido50.getNumero());
            votacaoService.cadVoto(local3.getCep(), partido50.getNumero());
            votacaoService.cadVoto(local3.getCep(), partido60.getNumero());
            votacaoService.cadVoto(local3.getCep(), partido60.getNumero());
            votacaoService.cadVoto(local3.getCep(), partido60.getNumero());
            votacaoService.cadVoto(local3.getCep(), partido60.getNumero());
        };
    }
}