package com.acmepolling.acme.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RemoveCandidatoDto (
        @NotBlank
        String codigo
){}
