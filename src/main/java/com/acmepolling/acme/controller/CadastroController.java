package com.acmepolling.acme.controller;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acmepolling.acme.common.ApiConstants;
import com.acmepolling.acme.controller.dto.response.ResponseCandidatoDto;
import com.acmepolling.acme.controller.mapper.CandidatoMapper;
import com.acmepolling.acme.service.candidato.CandidatoService;

@RestController
@RequestMapping(ApiConstants.API_CADASTRO)
public class CadastroController {

    private final CandidatoService service;
    private final CandidatoMapper mapper;

    public CadastroController(CandidatoService service, CandidatoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/listacandidatos")
    public ResponseEntity<ArrayList<ResponseCandidatoDto>> getTodos() {
        ArrayList<ResponseCandidatoDto> candidatos = service.getTodos().stream()
            .map(c -> mapper.toDto(c))
            .collect(Collectors.toCollection(ArrayList::new));
        
        return ResponseEntity.ok(candidatos);
    }
    //@PostMapping("/lista")
}