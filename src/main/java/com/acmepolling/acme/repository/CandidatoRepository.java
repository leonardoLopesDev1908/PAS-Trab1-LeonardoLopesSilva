package com.acmepolling.acme.repository;

import java.util.*;

import com.acmepolling.acme.controller.dto.response.CandidatoVotosCount;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.acmepolling.acme.entity.Candidato;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, UUID> {
    List<Candidato> findByNumero(String numero);

    Optional<Candidato> findByNumeroAndLocalidadeCep(String numero, String cep);

    Boolean existsByNumero(String numero);

    @Query("SELECT c FROM Candidato c WHERE c.situacao = com.acmepolling.acme.entity.enums.CandidatoSituacao.ELEITO AND c.localidade.cep = :cep ")
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

    @Query("""
        SELECT c 
        FROM Candidato c 
        LEFT JOIN Voto v ON v.candidato = c 
        WHERE c.localidade.cep = :cep 
            AND c.situacao = com.acmepolling.acme.entity.enums.CandidatoSituacao.ELEGIVEL
        GROUP BY c 
        ORDER BY COUNT(v) DESC, MAX(v.hora) ASC
    """)
    List<Candidato> findMaisVotadosPorCep(@Param("cep") String cep, Pageable pageable);

    default Candidato getMaisVotado(String cep) {
        List<Candidato> resultados = findMaisVotadosPorCep(cep, PageRequest.of(0, 1));
        return resultados.isEmpty() ? null : resultados.get(0);
    }
}