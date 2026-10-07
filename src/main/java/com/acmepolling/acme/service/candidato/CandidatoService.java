package com.acmepolling.acme.service.candidato;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.acmepolling.acme.controller.dto.response.CandidatoVotosCount;
import com.acmepolling.acme.entity.Candidato;
import org.apache.coyote.BadRequestException;

public interface CandidatoService {

    Candidato atualizaCandidato(String numero, String cep, String situacao) throws BadRequestException;

    Boolean cadastrarCandidato(Candidato candidato);

    List<CandidatoVotosCount> countVotosCandidatoPorPartido(String partido);

    Boolean existPorNumero(String numero);

    List<Candidato> getCandidatoPorNumero(String numero);

    List<CandidatoVotosCount> getCandidatosPorLocalidade(String cep);

    Candidato getEleitoPorLocalidade(String cep);

    Candidato getPorId(UUID id);

    Candidato getPorNumeroELocalidade(String numero, String cep);

    ArrayList<Candidato> getTodos();

    ArrayList<Candidato> getTodosPorLocalSituacao(String cep, String situacao);

    Boolean removeCandidato(String numero, String cep);

    Candidato getMaisVotadoPorLocalizacao(String cep);
}
