package com.acmepolling.acme.service.partido;

import com.acmepolling.acme.entity.Candidato;
import com.acmepolling.acme.entity.Partido;
import com.acmepolling.acme.repository.PartidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartidoServiceImpl implements PartidoService{

    private final PartidoRepository repository;

    public PartidoServiceImpl(PartidoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Partido> getTodos() {
        return repository.findAll();
    }

    @Override
    public Boolean existePorCodigo(String codigo) {
        return repository.existsByCodigo(codigo);
    }

    @Override
    public Partido getPorCodigo(String codigo) {
        Partido partido = repository.findByCodigo(codigo).orElseThrow(() -> {
            throw new EntityNotFoundException();
        });

        return partido;
    }

    @Override
    public Partido cadastraPartido() {
        return null;
    }
}
