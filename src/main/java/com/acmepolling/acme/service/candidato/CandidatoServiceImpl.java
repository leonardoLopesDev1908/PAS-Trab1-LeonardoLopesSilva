package com.acmepolling.acme.service.candidato;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.acmepolling.acme.controller.dto.response.CandidatoVotosCount;
import com.acmepolling.acme.entity.Localidade;
import com.acmepolling.acme.entity.Partido;
import com.acmepolling.acme.entity.enums.CandidatoSituacao;
import com.acmepolling.acme.service.localidade.LocalidadeService;
import com.acmepolling.acme.service.partido.PartidoService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import com.acmepolling.acme.entity.Candidato;
import com.acmepolling.acme.repository.CandidatoRepository;

@Service
public class CandidatoServiceImpl implements CandidatoService {

    CandidatoRepository repository;
    LocalidadeService localidadeService;
    PartidoService partidoService;

    public CandidatoServiceImpl(CandidatoRepository repository, LocalidadeService localidadeService,
                                PartidoService partidoService) {
        this.repository = repository;
        this.localidadeService = localidadeService;
        this.partidoService = partidoService;
    }

    @Override
    public Boolean cadastrarCandidato(Candidato candidato) {
        if(!partidoService.existePorNumero(candidato.getNumero().substring(0, 2))) {
            System.out.println("Não existe partido com esse código");
            return false;
        }
        Partido partido = partidoService.getPorNumero(candidato.getNumero());
        candidato.setPartido(partido);

        String cep = candidato.getLocalidade().getCep();
        if(!localidadeService.existePorCep(cep)) {
            localidadeService.cadastraLocalidade(cep);
        }

        Localidade localidade = localidadeService.getPorCep(cep);

        candidato.setLocalidade(localidade);
        candidato.setSituacao(CandidatoSituacao.PRE_CANDIDATO);

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
    public Candidato getPorNumeroELocalidade(String numero, String cep) {
        Candidato candidato = repository.findByNumeroAndLocalidadeCep(numero, cep)
                .orElseThrow(() -> new EntityNotFoundException("Candidato não encontrado no CEP informado"));

        return candidato;
    }

    @Override
    public ArrayList<Candidato> getTodos() {
        ArrayList<Candidato> candidatos = new ArrayList<>(repository.findAll());

        return candidatos;
    }

    @Override
    public ArrayList<Candidato> getTodosPorLocalSituacao(String cep, String situacao) {
        ArrayList<Candidato> candidatos = repository.findAll().stream()
                .filter(c -> c.getSituacao().equals(CandidatoSituacao.valueOf(situacao)))
                .filter(c -> c.getLocalidade().getCep().equals(cep))
                .collect(Collectors.toCollection(ArrayList::new));

        return candidatos;
    }

    @Override
    public Candidato atualizaCandidato(String numero, String cep, String situacao) throws BadRequestException {
        Candidato candidato = getPorNumeroELocalidade(numero, cep);
        CandidatoSituacao statusAtual = candidato.getSituacao();
        CandidatoSituacao novoStatus = CandidatoSituacao.valueOf(situacao);

        if(novoStatus == statusAtual) throw new BadRequestException();
        else if(novoStatus == CandidatoSituacao.PRE_CANDIDATO) throw new BadRequestException();
        else if((statusAtual == CandidatoSituacao.PRE_CANDIDATO ||
                statusAtual == CandidatoSituacao.INELEGIVEL     ||
                statusAtual == CandidatoSituacao.REMOVIDO)      &&
                (novoStatus == CandidatoSituacao.ELEITO         ||
                novoStatus == CandidatoSituacao.NAO_ELEITO)
        ) throw new BadRequestException();
        else if((statusAtual == CandidatoSituacao.ELEGIVEL &&
                novoStatus == CandidatoSituacao.REMOVIDO)  ||
                (statusAtual == CandidatoSituacao.REMOVIDO &&
                novoStatus == CandidatoSituacao.ELEGIVEL)
        ) throw new BadRequestException();
        
        candidato.setSituacao(novoStatus);
        repository.save(candidato);

        return candidato;
    }

    @Override
    public Boolean removeCandidato(String numero, String cep) {
        if(!localidadeService.existePorCep(cep)) throw new EntityNotFoundException();
        if(!repository.existsByNumero(numero)) throw new EntityNotFoundException();

        Candidato candidato = repository.findByNumeroAndLocalidadeCep(numero, cep).orElseThrow(() -> {
            throw new EntityNotFoundException();
        });

        candidato.setSituacao(CandidatoSituacao.REMOVIDO);
        repository.save(candidato);

        return true;
    }

    @Override
    public Candidato getMaisVotadoPorLocalizacao(String cep) {
        Candidato candidato = repository.getMaisVotado(cep);
        return candidato;
    }

    @Override
    public List<Candidato> getCandidatoPorNumero(String numero) {
        List<Candidato> candidato = repository.findByNumero(numero);

        if(candidato.isEmpty()) throw new EntityNotFoundException();
        return candidato;
    }

    @Override
    public Candidato getEleitoPorLocalidade(String cep) {
        Optional<Candidato> candidato = Optional.ofNullable(repository.getEleitoPorLocalidade(cep).orElseThrow(() -> {
            throw new EntityNotFoundException();
        }));
        return candidato.get();
    }

    @Override
    public List<CandidatoVotosCount> getCandidatosPorLocalidade(String cep) {
        List<CandidatoVotosCount> resultados = repository.countVotosPorCandidato(cep);

        return resultados;
    }

    @Override
    public List<CandidatoVotosCount> countVotosCandidatoPorPartido(String partido) {
        List<CandidatoVotosCount> resultados = repository.countVotosCandidatoPorPartido(partido);

        return resultados;
    }

    @Override
    public Boolean existPorNumero(String numero) {
        return repository.existsByNumero(numero);
    }

}
