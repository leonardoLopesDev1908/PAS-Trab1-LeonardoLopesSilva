package com.acmepolling.acme.service.voto;

import com.acmepolling.acme.entity.Candidato;
import com.acmepolling.acme.entity.Localidade;
import com.acmepolling.acme.entity.Voto;
import com.acmepolling.acme.repository.VotoRepository;
import com.acmepolling.acme.service.candidato.CandidatoService;
import com.acmepolling.acme.service.localidade.LocalidadeService;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;

@Service
public class VotacaoServiceImpl implements VotacaoService{

    private final VotoRepository repository;

    private final CandidatoService candidatoService;
    private final LocalidadeService localidadeService;
    private final LocalTime HORARIO_MINIMO_VOTACAO = LocalTime.of(8, 0);
    private final LocalTime HORARIO_MAXIMO_VOTACAO = LocalTime.of(17, 0);

    public VotacaoServiceImpl(CandidatoService candidatoService, VotoRepository repository,
                              LocalidadeService localidadeService) {
        this.candidatoService = candidatoService;
        this.repository = repository;
        this.localidadeService = localidadeService;
    }

    @Override
    public Boolean cadVoto(String cep, String numero) {
        Voto voto = new Voto();
        LocalDateTime now = LocalDateTime.now();
        voto.setHora(now);

        LocalTime horaAtual = now.toLocalTime();

//        voto.setIsValid(!horaAtual.isBefore(HORARIO_MINIMO_VOTACAO)
//                     && !horaAtual.isAfter(HORARIO_MAXIMO_VOTACAO)
//        );

        Candidato candidato = candidatoService.getPorNumeroELocalidade(numero, cep);
        voto.setCandidato(candidato);

        Localidade localidade = localidadeService.getPorCep(cep);
        voto.setLocalidade(localidade);

        repository.save(voto);

        return voto.getIsValid();
    }

    @Override
    public Long getVotosValidosPorCandidato(String numero) {
        return repository.getVotosValidosPorCandidato(numero);
    }

    @Override
    public Long getVotosInvalidosPorCandidato(String numero) {
        return repository.getVotosInvalidosPorCandidato(numero);
    }
}
