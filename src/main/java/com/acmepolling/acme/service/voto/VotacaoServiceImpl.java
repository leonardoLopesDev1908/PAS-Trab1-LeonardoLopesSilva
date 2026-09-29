package com.acmepolling.acme.service.voto;

import com.acmepolling.acme.entity.Candidato;
import com.acmepolling.acme.entity.Voto;
import com.acmepolling.acme.service.candidato.CandidatoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;

@Service
public class VotacaoServiceImpl implements VotacaoService{

    private final CandidatoService candidatoService;
    private final LocalTime HORARIO_MINIMO_VOTACAO = LocalTime.of(8, 0);
    private final LocalTime HORARIO_MAXIMO_VOTACAO = LocalTime.of(17, 0);

    public VotacaoServiceImpl(CandidatoService candidatoService) {
        this.candidatoService = candidatoService;
    }

    @Override
    public Boolean cadVoto(String cep, String numero) {
        LocalDateTime now = LocalDateTime.now();
        if(now.isBefore(ChronoLocalDateTime.from(HORARIO_MINIMO_VOTACAO)) ||
                now.isAfter(ChronoLocalDateTime.from(HORARIO_MAXIMO_VOTACAO))) {
            return false;
        }
        //chamar apiExterna para pegar endereço com CEP
        //https://www.gov.br/conecta/catalogo/apis/cep-codigo-de-enderecamento-postal/swagger%20-1.json/swagger_view#tag/Consultar-CEP/paths/~1consulta~1cep~1{cep}/get
        Candidato candidato = candidatoService.getCandidatoPorNumero(numero);
        //candidato.setLocalidade(localidade);

        return true;
    }
}
