package com.acmepolling.acme.service.apuracao;

import com.acmepolling.acme.controller.dto.request.ResponseCandidatoVotos;
import com.acmepolling.acme.controller.dto.response.ResponseCandidatoDto;
import com.acmepolling.acme.controller.dto.response.ResponseLocalidadeDto;
import com.acmepolling.acme.entity.Candidato;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

public interface ApuracaoService {

    Candidato consultaEleito(String cep);

    HashMap<ResponseLocalidadeDto, Long> consultaLocalidade(String cep);

    HashMap<ResponseCandidatoDto, Long> maisVotadosPorPartido();

    HashMap<String, Long> consultaVotosCandidato(String numero);
}
