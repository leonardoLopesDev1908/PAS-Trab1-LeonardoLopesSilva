package com.acmepolling.acme.repository;

import java.util.*;

import com.acmepolling.acme.controller.dto.response.CandidatoVotosCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.acmepolling.acme.entity.Candidato;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, UUID> {
    Optional<Candidato> findByNumero(String numero);

    @Query("SELECT c FROM Candidato c WHERE c.status = 'ELEITO' AND c.cep = :cep ")
    Candidato getEleitoPorLocalidade(String cep);

    @Query("""
        SELECT new com.acmepolling.acme.controller.dto.CandidatoVotosCount(
            v.candidato.nome, 
            v.candidato.numero, 
            v.candidato.partido.nome, 
            COUNT(V)
        ) 
        FROM Voto v 
        WHERE v.candidato.localidade.cep = :cep
        GROUP BY v.candidato.nome, v.candidato.codigo, v.candidato.partido.nome
    """)
    List<CandidatoVotosCount> countVotosPorCandidato(String cep);

    @Query("""
        SELECT new com.acmepolling.acme.controller.dto.CandidatoVotosCount(
            v.candidato.nome, 
            v.candidato.numero, 
            v.candidato.partido.nome, 
            COUNT(V)
        )
        From Voto v
        WHERE v.candidato.partido.nome = :partido
        SORT BY COUNT(V)
    """)
    List<CandidatoVotosCount> countVotosCandidatoPorPartido(String partido);
}