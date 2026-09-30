package com.acmepolling.acme.repository;

import com.acmepolling.acme.entity.Localidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LocalidadeRepository extends JpaRepository<Localidade, UUID> {
    Optional<Localidade> findByCep(String cep);

    boolean existsByCep(String cep);
}