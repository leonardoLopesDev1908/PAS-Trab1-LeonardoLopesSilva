package com.acmepolling.acme.controller.dto.response;

public record ResponseVotosDto(
    Long votosValidos,
    Long votosInvalidos
) {}
