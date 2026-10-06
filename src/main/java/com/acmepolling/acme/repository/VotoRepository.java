package com.acmepolling.acme.repository;

import com.acmepolling.acme.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VotoRepository extends JpaRepository<Voto, UUID> {

    @Query("""
    SELECT COUNT(v)
    FROM Voto v
    WHERE v.candidato.codigo = :codigo 
      AND v.isValid = true
    """)
    Long getVotosValidosPorCandidato(@Param("codigo") String codigo);

    @Query("""
    SELECT COUNT(v)
    FROM Voto v
    WHERE v.candidato.codigo = :codigo 
      AND v.isValid = false
    """)
    Long getVotosInvalidosPorCandidato(@Param("codigo") String codigo);
}