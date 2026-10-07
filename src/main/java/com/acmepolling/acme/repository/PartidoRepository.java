package com.acmepolling.acme.repository;

import com.acmepolling.acme.entity.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, UUID> {
    boolean existsByNumero(String numero);
    Optional<Partido> findByNumero(String numero);
}