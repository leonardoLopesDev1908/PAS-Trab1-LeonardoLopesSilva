package com.acmepolling.acme.controller.dto.response;

public record CandidatoVotosCount(
    String nome,
    String numero,
    String partido,
    Long votos
) {}
