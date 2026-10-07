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
    Optional<Candidato> findByNumero(String numero);

    Boolean existsByNumero(String numero);

    @Query("SELECT c FROM Candidato c WHERE c.status = com.acmepolling.acme.entity.enums.CandidatoStatus.ELEITO AND c.localidade.cep = :cep ")
    Optional<Candidato> getEleitoPorLocalidade(@Param("cep") String cep);

    @Query("""
                SELECT new com.acmepolling.acme.controller.dto.response.CandidatoVotosCount(
                    v.candidato.nome,
                    v.candidato.numero, 
                    v.candidato.partido.nome, 
                    COUNT(v)
                ) 
                FROM Voto v 
                WHERE v.candidato.localidade.cep = :cep AND v.isValid = true
                GROUP BY v.candidato.nome, v.candidato.numero, v.candidato.partido.nome
            """)
    List<CandidatoVotosCount> countVotosPorCandidato(@Param("cep") String cep);

    @Query("""
            SELECT new com.acmepolling.acme.controller.dto.response.CandidatoVotosCount(
                v.candidato.nome, 
                v.candidato.numero, 
                v.candidato.partido.nome, 
                COUNT(v)
            )
            FROM Voto v
            WHERE v.candidato.partido.nome = :partido AND v.isValid = true
            GROUP BY v.candidato.nome, v.candidato.numero, v.candidato.partido.nome
            ORDER BY COUNT(v) DESC
            """)
    List<CandidatoVotosCount> countVotosCandidatoPorPartido(@Param("partido") String partido);
}