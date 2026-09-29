package com.acmepolling.acme.service.candidato;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.acmepolling.acme.entity.Candidato;
import com.acmepolling.acme.repository.CandidatoRepository;

@Service
public class CandidatoServiceImpl implements CandidatoService {

    CandidatoRepository repository;

    public CandidatoServiceImpl(CandidatoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Candidato cadastrarCandidato(Candidato candidato) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cadastrarCandidato'");
    }

    @Override
    public Candidato getPorId(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPorId'");
    }

    @Override
    public ArrayList<Candidato> getTodos() {
        ArrayList<Candidato> candidatos = repository.findAll();

        return candidatos;
    }
    
}
