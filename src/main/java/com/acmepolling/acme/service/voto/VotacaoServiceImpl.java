package com.acmepolling.acme.service.voto;

import com.acmepolling.acme.entity.Candidato;
import com.acmepolling.acme.entity.Voto;
import com.acmepolling.acme.repository.VotoRepository;
import com.acmepolling.acme.service.candidato.CandidatoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;

@Service
public class VotacaoServiceImpl implements VotacaoService{

    private final VotoRepository repository;

    private final CandidatoService candidatoService;
    private final LocalTime HORARIO_MINIMO_VOTACAO = LocalTime.of(8, 0);
    private final LocalTime HORARIO_MAXIMO_VOTACAO = LocalTime.of(17, 0);

    public VotacaoServiceImpl(CandidatoService candidatoService, VotoRepository repository) {
        this.candidatoService = candidatoService;
        this.repository = repository;
    }

    @Override
    public Boolean cadVoto(String cep, String codigo) {
        Voto voto = new Voto();
        LocalDateTime now = LocalDateTime.now();
        voto.setHora(now);

        if(now.isBefore(ChronoLocalDateTime.from(HORARIO_MINIMO_VOTACAO)) ||
                now.isAfter(ChronoLocalDateTime.from(HORARIO_MAXIMO_VOTACAO))) {
            voto.setIsValid(false);
        }
        //chamar apiExterna para pegar endereço com CEP
        //https://www.gov.br/conecta/catalogo/apis/cep-codigo-de-enderecamento-postal/swagger%20-1.json/swagger_view#tag/Consultar-CEP/paths/~1consulta~1cep~1{cep}/get
        Candidato candidato = candidatoService.getCandidatoPorCodigo(codigo);
        //candidato.setLocalidade(localidade);

        return true;
    }

    @Override
    public Long getVotosValidosPorCandidato(String codigo) {
        return repository.getVotosValidosPorCandidato(codigo);
    }

    @Override
    public Long getVotosInvalidosPorCandidato(String codigo) {
        return repository.getVotosInvalidosPorCandidato(codigo);
    }
}
