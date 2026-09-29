package com.acmepolling.acme.service.candidato;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

import com.acmepolling.acme.controller.dto.request.CreateCandidatoDto;
import jakarta.persistence.EntityNotFoundException;
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
    public Boolean cadastrarCandidato(Candidato candidato) {
        Candidato candidatoSalvo = repository.save(candidato);
        return candidatoSalvo == null ? Boolean.FALSE : Boolean.TRUE;
    }

    @Override
    public Candidato getPorId(UUID id) {
        Candidato candidato = repository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Candidato não encontrado");
        });

        return candidato;
    }

    @Override
    public ArrayList<Candidato> getTodos() {
        ArrayList<Candidato> candidatos = repository.findAll();

        return candidatos;
    }

    @Override
    public ArrayList<Candidato> getTodosPorLocalSituacao(String cep, String situacao) {
        ArrayList<Candidato> candidatos = repository.findAll().stream()
                .filter(c -> c.getStatus().equals(situacao))
                .filter(c -> c.getCep().equals(cep))
                .collect(Collectors.toCollection(ArrayList::new));

        return candidatos;
    }

    @Override
    public Boolean removeCandidato(int codigo) {
        return null;
    }

}
