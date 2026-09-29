package com.acmepolling.acme.service.candidato;

import java.util.ArrayList;
import java.util.UUID;

import com.acmepolling.acme.entity.Candidato;

public interface CandidatoService {
    Candidato cadastrarCandidato(Candidato candidato);

    Candidato getPorId(UUID id);

    ArrayList<Candidato> getTodos();
}