package com.acmepolling.acme.controller.dto.response;

public record ResponseMaisVotadoDto (
    String numero,
    String partido,
    String nome,
    int votos
){}
