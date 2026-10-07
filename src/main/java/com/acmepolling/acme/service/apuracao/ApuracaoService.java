package com.acmepolling.acme.service.apuracao;

import com.acmepolling.acme.controller.dto.response.CandidatoVotosCount;
import com.acmepolling.acme.controller.dto.response.ResponseCandidatoDto;
import com.acmepolling.acme.controller.dto.response.ResponseVotosDto;
import com.acmepolling.acme.entity.Candidato;

import java.util.List;

public interface ApuracaoService {

    Candidato consultaEleito(String cep);

    //Esse método serve para consultar eleitos mesmo antes da apuração
    Candidato consultaEleitoTeste(String cep);

    List<CandidatoVotosCount> consultaLocalidade(String cep);

    List<CandidatoVotosCount> maisVotadosPorPartido();

    ResponseVotosDto consultaVotosCandidato(String numero);

    List<Candidato> apura();
}
