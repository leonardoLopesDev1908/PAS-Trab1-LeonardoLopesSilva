package com.acmepolling.acme.service.localidade;

import com.acmepolling.acme.entity.Localidade;

import java.util.List;

public interface LocalidadeService {

    List<Localidade> getTodas();

    Localidade getPorCep(String cep);

    boolean existePorCep(String cep);
}