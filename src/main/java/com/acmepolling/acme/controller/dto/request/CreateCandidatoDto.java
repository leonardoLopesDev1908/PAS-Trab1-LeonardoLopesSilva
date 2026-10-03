package com.acmepolling.acme.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateCandidatoDto(
    @NotBlank 
    String nome,

    @NotBlank 
    String codigo,

    @NotBlank 
    String cep
) {}