package com.acmepolling.acme.controller.dto.response;

public record CandidatoVotosCount(
    String nome,
    String codigo,
    String partido,
    Long votos
) {}
