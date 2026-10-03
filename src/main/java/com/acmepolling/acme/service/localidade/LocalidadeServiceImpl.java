package com.acmepolling.acme.service.localidade;

import com.acmepolling.acme.entity.Localidade;
import com.acmepolling.acme.external.CepApi;
import com.acmepolling.acme.repository.LocalidadeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.boot.context.properties.source.ConfigurationPropertyName.isValid;

@Service
public class LocalidadeServiceImpl implements LocalidadeService{

    LocalidadeRepository repository;

    public LocalidadeServiceImpl(LocalidadeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void cadastraLocalidade(String cep) {
        if(!isCepValid(cep))
        {
            System.out.println("CEP INVÁLIDO");
            return;
        }

        Localidade localidade = CepApi.getLocalidade(cep);
        repository.save(localidade);
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

    private boolean isCepValid(String cep) {
        if(cep == null) return false;
        return cep.matches("^[0-9]{8}$");
    }
}
