package com.acmepolling.acme.service.candidato;

import java.util.ArrayList;
import java.util.UUID;

import com.acmepolling.acme.entity.Candidato;

public interface CandidatoService {
    Boolean cadastrarCandidato(Candidato candidato);

    Candidato getPorId(UUID id);

    ArrayList<Candidato> getTodos();

    ArrayList<Candidato> getTodosPorLocalSituacao(String cep, String situacao);

    Candidato atualizaCandidato(String numero, String situacao);

    Boolean removeCandidato(String numero);

    Candidato getCandidatoPorNumero(String numero);
}
