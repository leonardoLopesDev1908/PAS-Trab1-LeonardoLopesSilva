package com.acmepolling.acme.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePartidoDto(
    @NotNull
    Integer numero,
    @NotBlank
    String nome
) {}
