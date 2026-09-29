package com.acmepolling.acme.service.candidato;

import java.util.ArrayList;
import java.util.UUID;

import com.acmepolling.acme.controller.dto.request.CreateCandidatoDto;
import com.acmepolling.acme.entity.Candidato;

public interface CandidatoService {
    Boolean cadastrarCandidato(Candidato candidato);

    Candidato getPorId(UUID id);

    ArrayList<Candidato> getTodos();

    ArrayList<Candidato> getTodosPorLocalSituacao(String cep, String situacao);

    Boolean removeCandidato(int codigo);
}