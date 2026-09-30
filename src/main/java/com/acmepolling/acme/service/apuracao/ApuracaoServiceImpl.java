package com.acmepolling.acme.service.apuracao;

import com.acmepolling.acme.controller.dto.response.CandidatoVotosCount;
import com.acmepolling.acme.controller.dto.response.ResponseVotosDto;
import com.acmepolling.acme.entity.Candidato;
import com.acmepolling.acme.entity.Partido;
import com.acmepolling.acme.repository.CandidatoRepository;
import com.acmepolling.acme.repository.LocalidadeRepository;
import com.acmepolling.acme.repository.PartidoRepository;
import com.acmepolling.acme.repository.VotoRepository;
import com.acmepolling.acme.service.candidato.CandidatoService;
import com.acmepolling.acme.service.localidade.LocalidadeService;
import com.acmepolling.acme.service.partido.PartidoService;
import com.acmepolling.acme.service.voto.VotacaoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ApuracaoServiceImpl implements ApuracaoService{

    private final CandidatoService candidatoService;
    private final LocalidadeService localidadeService;
    private final VotacaoService votoService;
    private final PartidoService partidoService;

    public ApuracaoServiceImpl(CandidatoService candidatoService, LocalidadeService localidadeService,
                               VotacaoService votoService, PartidoService partidoService) {
        this.candidatoService = candidatoService;
        this.localidadeService = localidadeService;
        this.votoService = votoService;
        this.partidoService = partidoService;
    }

    @Override
    public Candidato consultaEleito(String cep) {
        //testar horário
        if(LocalDateTime.now().getHour() < 17) {
            System.out.println("Horário de apuração ainda não começou");
            return null;
        }
        //testar se cep existe
        if(!localidadeService.existePorCep(cep)){
            throw new EntityNotFoundException();
        }

        Candidato candidato = candidatoService.getEleitoPorLocalidade(cep);

        return candidato;
    }

    @Override
    public List<CandidatoVotosCount> consultaLocalidade(String cep) {
        List<CandidatoVotosCount> resultados = candidatoService.getCandidatosPorLocalidade(cep);

        return resultados;
    }

    @Override
    public List<CandidatoVotosCount> maisVotadosPorPartido() {
        List<Partido> partidos = partidoService.getTodos();
        List<CandidatoVotosCount> resultado = new ArrayList<>();

        for(Partido p : partidos)
        {
            resultado.add(candidatoService.countVotosCandidatoPorPartido(p.getNome()).getFirst());
        }
        return resultado;
    }

    @Override
    public ResponseVotosDto consultaVotosCandidato(String numero) {
        Long votosValidos = votoService.getVotosValidosPorCandidato(numero);
        Long votosInvalidos = votoService.getVotosInvalidosPorCandidato(numero);

        return new ResponseVotosDto(votosValidos, votosInvalidos);
    }
}
