package com.acmepolling.acme.controller;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.acmepolling.acme.controller.dto.request.CreateCandidatoDto;
import com.acmepolling.acme.controller.dto.request.RemoveCandidatoDto;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/listacandidatoslocalidade/{cep}/situacao/{situacao}")
    public ResponseEntity<ArrayList<ResponseCandidatoDto>> getTodosPorLocalSituacao(@PathVariable String cep,
                                                                                    @PathVariable String situacao) {
        ArrayList<ResponseCandidatoDto> candidatos = service.getTodosPorLocalSituacao(cep, situacao).stream()
                .map(c -> mapper.toDto(c))
                .collect(Collectors.toCollection(ArrayList::new));

        return ResponseEntity.ok(candidatos);
    }

    @PostMapping("/cadcandidato")
    public ResponseEntity<Boolean> cadastrarCandidato(@RequestBody CreateCandidatoDto dto) {
        Boolean success = service.cadastrarCandidato(mapper.toEntity(dto));
        return ResponseEntity.ok(success);
    }

    @PutMapping("/atualizandidato/{numero}/{cep}/situacao/{situacao}")
    public ResponseEntity<ResponseCandidatoDto> atualizaCandidato(@PathVariable String numero,
                                                                  @PathVariable String cep,
                                                                  @PathVariable String situacao) throws BadRequestException {
        ResponseCandidatoDto response = mapper.toDto(service.atualizaCandidato(numero, cep, situacao));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/removecandidato")
    public ResponseEntity<Boolean> removeCandidato(@RequestBody RemoveCandidatoDto dto) {
        Boolean success = service.removeCandidato(dto.numero(), dto.cep());
        return ResponseEntity.ok(success);
    }
}