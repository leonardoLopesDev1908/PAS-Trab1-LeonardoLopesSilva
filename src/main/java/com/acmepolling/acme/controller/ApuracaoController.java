package com.acmepolling.acme.controller;

import com.acmepolling.acme.common.ApiConstants;
import com.acmepolling.acme.controller.dto.response.*;
import com.acmepolling.acme.controller.mapper.CandidatoMapper;
import com.acmepolling.acme.service.apuracao.ApuracaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiConstants.API_APURACAO)
public class ApuracaoController {

    private final ApuracaoService service;
    private final CandidatoMapper candidatoMapper;

    public ApuracaoController(ApuracaoService service, CandidatoMapper candidatoMapper) {
        this.service = service;
        this.candidatoMapper = candidatoMapper;
    }

    @GetMapping("/consultaeleito")
    public ResponseEntity<ResponseCandidatoDto> consultaEleito(@RequestParam String cep) {
        ResponseCandidatoDto response = candidatoMapper.toDto(service.consultaEleito(cep));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/consultalocalidade")
    public ResponseEntity<ArrayList<CandidatoVotosCount>> consultaLocalidade(@RequestParam String cep) {
        ArrayList<CandidatoVotosCount> candidatos = new ArrayList<>(service.consultaLocalidade(cep));
        return ResponseEntity.ok(candidatos);
    }

    @GetMapping("/consultacandidato")
    public ResponseEntity<ResponseVotosDto> consultaCandidato(@RequestParam String codigo) {
        ResponseVotosDto response = service.consultaVotosCandidato(codigo);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/listamaisvotadospartido")
    public ResponseEntity<ArrayList<CandidatoVotosCount>> consultaMaisVotados() {
        ArrayList<CandidatoVotosCount> maisVotados = new ArrayList<>(service.maisVotadosPorPartido());

        return ResponseEntity.ok(maisVotados);
    }

//    @GetMapping("/apurar")
//    public ResponseEntity<ResponseCandidatoDto> apuracao() {
//        ArrayList<ResponseCandidatoDto>
//
//        return ResponseEntity.ok();
//    }
}
