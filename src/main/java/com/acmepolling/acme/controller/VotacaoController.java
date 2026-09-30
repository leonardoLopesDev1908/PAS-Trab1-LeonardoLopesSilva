package com.acmepolling.acme.controller;

import com.acmepolling.acme.common.ApiConstants;
import com.acmepolling.acme.controller.dto.request.VotoRequest;
import com.acmepolling.acme.service.voto.VotacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.API_VOTACAO)
public class VotacaoController {

    private final VotacaoService service;

    public VotacaoController(VotacaoService service) {
        this.service = service;
    }

    @PostMapping("/cadvoto")
    public ResponseEntity<Boolean> cadVoto(@RequestBody VotoRequest voto) {
        Boolean success = service.cadVoto(voto.cep(), voto.numero());
        return ResponseEntity.ok(success);
    }
}
