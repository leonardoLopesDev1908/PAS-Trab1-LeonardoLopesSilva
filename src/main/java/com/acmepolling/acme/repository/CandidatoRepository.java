package com.acmepolling.acme.repository;

import java.util.*;

import com.acmepolling.acme.controller.dto.response.CandidatoVotosCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.acmepolling.acme.entity.Candidato;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, UUID> {
    Optional<Candidato> findByCodigo(String codigo);

    Boolean existsByCodigo(String codigo);

    @Query("SELECT c FROM Candidato c WHERE c.status = com.acmepolling.acme.entity.enums.CandidatoStatus.ELEITO AND c.localidade.cep = :cep ")
    Optional<Candidato> getEleitoPorLocalidade(@Param("cep") String cep);

    @Query("""
                SELECT new com.acmepolling.acme.controller.dto.response.CandidatoVotosCount(
                    v.candidato.nome,
                    v.candidato.codigo, 
                    v.candidato.partido.nome, 
                    COUNT(v)
                ) 
                FROM Voto v 
                WHERE v.candidato.localidade.cep = :cep
                GROUP BY v.candidato.nome, v.candidato.codigo, v.candidato.partido.nome
            """)
    List<CandidatoVotosCount> countVotosPorCandidato(@Param("cep") String cep);

    @Query("""
            SELECT new com.acmepolling.acme.controller.dto.response.CandidatoVotosCount(
                v.candidato.nome, 
                v.candidato.codigo, 
                v.candidato.partido.nome, 
                COUNT(v)
            )
            FROM Voto v
            WHERE v.candidato.partido.nome = :partido
            GROUP BY v.candidato.nome, v.candidato.codigo, v.candidato.partido.nome
            ORDER BY COUNT(v) DESC
            """)
    List<CandidatoVotosCount> countVotosCandidatoPorPartido(@Param("partido") String partido);
}