package com.acmepolling.acme.service.candidato;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.acmepolling.acme.controller.dto.response.CandidatoVotosCount;
import com.acmepolling.acme.entity.Candidato;
import org.apache.coyote.BadRequestException;

public interface CandidatoService {

    Candidato atualizaCandidato(String numero, String situacao) throws BadRequestException;

    Boolean cadastrarCandidato(Candidato candidato);

    List<CandidatoVotosCount> countVotosCandidatoPorPartido(String partido);

    Boolean existPorCodigo(String codigo);

    Candidato getCandidatoPorCodigo(String codigo);

    List<CandidatoVotosCount> getCandidatosPorLocalidade(String cep);

    Candidato getEleitoPorLocalidade(String cep);

    Candidato getPorId(UUID id);

    ArrayList<Candidato> getTodos();

    ArrayList<Candidato> getTodosPorLocalSituacao(String cep, String situacao);

    Boolean removeCandidato(String numero);
}
