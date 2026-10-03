package com.acmepolling.acme.service.partido;

import com.acmepolling.acme.entity.Partido;

import java.util.List;

public interface PartidoService {
    List<Partido> getTodos();

    Boolean existePorCodigo(String codigo);

    Partido getPorCodigo(String codigo);

    Partido cadastraPartido();
}
