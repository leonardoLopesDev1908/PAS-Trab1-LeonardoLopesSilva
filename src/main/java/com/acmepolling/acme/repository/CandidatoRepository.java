package com.acmepolling.acme.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.acmepolling.acme.entity.Candidato;

@Component
public class CandidatoRepository {

    HashMap<UUID, Candidato> candidatos = new HashMap<>();

    public ArrayList<Candidato> findAll() {
        return new ArrayList<Candidato>(candidatos.values());
    }

    public void save(Candidato candidato) {
        candidatos.putIfAbsent(UUID.randomUUID(), candidato);
    }
}