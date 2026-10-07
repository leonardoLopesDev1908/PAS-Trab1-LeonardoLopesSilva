package com.acmepolling.acme.controller.dto.response;

import com.acmepolling.acme.entity.enums.CandidatoSituacao;

public record ResponseCandidatoDto(
    String nome,
    CandidatoSituacao status,
    String numero,
    String partido,
    String cep,
    String cidade,
    String estado
) {}
