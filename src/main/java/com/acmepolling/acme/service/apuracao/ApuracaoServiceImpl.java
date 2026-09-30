package com.acmepolling.acme.service.apuracao;

import com.acmepolling.acme.controller.dto.response.ResponseCandidatoDto;
import com.acmepolling.acme.controller.dto.response.ResponseLocalidadeDto;
import com.acmepolling.acme.entity.Candidato;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ApuracaoServiceImpl implements ApuracaoService{
    
    @Override
    public Candidato consultaEleito(String cep) {
        return null;
    }

    @Override
    public HashMap<ResponseLocalidadeDto, Long> consultaLocalidade(String cep) {
        return null;
    }

    @Override
    public HashMap<ResponseCandidatoDto, int> maisVotadosPorPartido() {
        
        return null;
    }

    @Override
    public HashMap<String, Long> consultaVotosCandidato(String numero) {
        return null;
    }
}
