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
        Localidade localidade = Localidade.builder()
                .cep(dto.cep())
                .build();

        return Candidato.builder()
            .nome(dto.nome())
            .codigo(dto.codigo())
            .localidade(localidade)
            .build();
    }

    public ResponseCandidatoDto toDto(Candidato candidato) {
        //PartidoResponseDto dto = partidoMapper.toDto(candidato.getPartido());
        ResponseCandidatoDto dto = new ResponseCandidatoDto(
            candidato.getNome(),
            candidato.getStatus(),
            candidato.getCodigo(),
            candidato.getPartido().getNome(),
            candidato.getLocalidade().getCep(),
            candidato.getLocalidade().getCidade(),
            candidato.getLocalidade().getEstado()
        );

        return dto;
    }
}
