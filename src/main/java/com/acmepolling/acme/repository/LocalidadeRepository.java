package com.acmepolling.acme.repository;

import com.acmepolling.acme.entity.Localidade;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LocalidadeRepository extends JpaRepository<Localidade, UUID> {
    Optional<Localidade> findByCep(String cep);

    boolean existsByCep(String cep);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("""
        UPDATE Candidato c
        SET c.situacao = CASE
            WHEN c.numero = :numero THEN com.acmepolling.acme.entity.enums.CandidatoSituacao.ELEITO
            ELSE com.acmepolling.acme.entity.enums.CandidatoSituacao.NAO_ELEITO
        END
        WHERE c.localidade.cep = :cep 
          AND c.situacao = com.acmepolling.acme.entity.enums.CandidatoSituacao.ELEGIVEL
        """)
    void apurar(@Param("cep") String cep, @Param("numero") String numero);
}