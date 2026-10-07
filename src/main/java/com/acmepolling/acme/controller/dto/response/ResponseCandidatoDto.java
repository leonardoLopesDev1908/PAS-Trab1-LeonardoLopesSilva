package com.acmepolling.acme.controller.dto.response;

import com.acmepolling.acme.entity.enums.CandidatoStatus;

public record ResponseCandidatoDto(
    String nome,
    CandidatoStatus status,
    String numero,
    String partido,
    String cep,
    String cidade,
    String estado
) {}
