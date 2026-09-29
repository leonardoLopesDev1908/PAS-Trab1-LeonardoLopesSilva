package com.acmepolling.acme.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.acmepolling.acme.entity.Candidato;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, UUID> {
    Optional<Candidato> findByNumero(String numero);
}