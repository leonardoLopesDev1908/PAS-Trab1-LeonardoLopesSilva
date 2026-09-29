package com.acmepolling.acme.repository;

import com.acmepolling.acme.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VotoRepository extends JpaRepository<Voto, UUID> {

}