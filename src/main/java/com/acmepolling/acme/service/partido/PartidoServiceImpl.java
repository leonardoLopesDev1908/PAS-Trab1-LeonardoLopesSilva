package com.acmepolling.acme.service.partido;

import com.acmepolling.acme.entity.Partido;
import com.acmepolling.acme.repository.PartidoRepository;
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
}
