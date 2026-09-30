package com.acmepolling.acme.service.localidade;

import com.acmepolling.acme.entity.Localidade;
import com.acmepolling.acme.repository.LocalidadeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalidadeServiceImpl implements LocalidadeService{

    LocalidadeRepository repository;

    public LocalidadeServiceImpl(LocalidadeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Localidade> getTodas() {
        List<Localidade> localidades = repository.findAll();
        return localidades;
    }

    @Override
    public Localidade getPorCep(String cep) {
        Optional<Localidade> localidade = repository.findByCep(cep);

        if(localidade.isEmpty())
            throw new EntityNotFoundException();

        return localidade.get();
    }

    @Override
    public boolean existePorCep(String cep) {
        return repository.existsByCep(cep);
    }
}
