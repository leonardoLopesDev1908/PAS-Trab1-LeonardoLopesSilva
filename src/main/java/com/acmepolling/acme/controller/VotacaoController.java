package com.acmepolling.acme.controller;

import com.acmepolling.acme.common.ApiConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.API_VOTACAO)
public class VotacaoController {

    //private final VotacaoService service;

    //public VotacaoController(VotacaoService service) {
        //this.service = service; }

    @PostMapping("/cadvoto")
    public ResponseEntity<Boolean> cadVoto() {
        Boolean success = true;
        return ResponseEntity.ok(success);
    }
}