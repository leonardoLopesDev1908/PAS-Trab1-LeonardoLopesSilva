package com.acmepolling.acme.service.partido;

import com.acmepolling.acme.entity.Partido;

import java.util.List;

public interface PartidoService {
    List<Partido> getTodos();

    Boolean existePorNumero(String numero);

    Partido getPorNumero(String numero);

    Partido cadastraPartido();
}
