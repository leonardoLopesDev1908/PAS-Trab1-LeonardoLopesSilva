package com.acmepolling.acme.service.voto;

import com.acmepolling.acme.controller.dto.request.VotoRequest;
import com.acmepolling.acme.entity.Voto;
import org.springframework.http.ResponseEntity;

public interface VotacaoService {
    Boolean cadVoto(String cep, String numero);
}