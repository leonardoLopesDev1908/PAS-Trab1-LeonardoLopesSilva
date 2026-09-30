package com.acmepolling.acme.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public record VotoRequest(
    @NotBlank
    String numero,
    @NotBlank
    String cep
) {}
