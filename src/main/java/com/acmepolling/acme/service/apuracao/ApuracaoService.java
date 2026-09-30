package com.acmepolling.acme.service.apuracao;

import com.acmepolling.acme.controller.dto.response.CandidatoVotosCount;
import com.acmepolling.acme.controller.dto.response.ResponseVotosDto;
import com.acmepolling.acme.entity.Candidato;
import com.acmepolling.acme.entity.Localidade;

import java.util.List;
import java.util.HashMap;

public interface ApuracaoService {

    Candidato consultaEleito(String cep);

    List<CandidatoVotosCount> consultaLocalidade(String cep);

    List<CandidatoVotosCount> maisVotadosPorPartido();

    ResponseVotosDto consultaVotosCandidato(String numero);
}
