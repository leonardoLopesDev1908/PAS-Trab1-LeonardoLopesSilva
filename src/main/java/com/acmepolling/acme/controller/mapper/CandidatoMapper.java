package com.acmepolling.acme.controller.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.acmepolling.acme.controller.dto.request.CreateCandidatoDto;
import com.acmepolling.acme.controller.dto.response.ResponseCandidatoDto;
import com.acmepolling.acme.entity.Candidato;
import com.acmepolling.acme.entity.Localidade;

@Component
public class CandidatoMapper {

    public Candidato toEntity(CreateCandidatoDto dto) {
        Localidade localidade = new Localidade(UUID.randomUUID(), dto.cidade(), dto.estado());
        return Candidato.builder()
            .nome(dto.nome())
            .partido(dto.partido())
            .localidade(localidade)
            .build();
    }

    public ResponseCandidatoDto toDto(Candidato candidato) {
        //PartidoResponseDto dto = partidoMapper.toDto(candidato.getPartido());
        ResponseCandidatoDto dto = new ResponseCandidatoDto(
            candidato.getNome(),
            candidato.getStatus(),
            candidato.getPartido(),
            candidato.getLocalidade().getCidade(),
            candidato.getLocalidade().getEstado()
        );

        return dto;
    }
}
