package com.acmepolling.acme.service.candidato;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.acmepolling.acme.controller.dto.response.CandidatoVotosCount;
import com.acmepolling.acme.entity.Localidade;
import com.acmepolling.acme.entity.Partido;
import com.acmepolling.acme.entity.enums.CandidatoStatus;
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
        if(!partidoService.existePorCodigo(candidato.getCodigo().substring(0, 2))) {
            System.out.println("Não existe partido com esse código");
            return false;
        }
        Partido partido = partidoService.getPorCodigo(candidato.getCodigo());
        candidato.setPartido(partido);

        String cep = candidato.getLocalidade().getCep();
        if(!localidadeService.existePorCep(cep)) {
            localidadeService.cadastraLocalidade(cep);
        }

        Localidade localidade = localidadeService.getPorCep(cep);

        candidato.setLocalidade(localidade);
        candidato.setStatus(CandidatoStatus.PRE_CANDIDATO);

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
    public ArrayList<Candidato> getTodos() {
        ArrayList<Candidato> candidatos = new ArrayList<>(repository.findAll());

        return candidatos;
    }

    @Override
    public ArrayList<Candidato> getTodosPorLocalSituacao(String cep, String situacao) {
        ArrayList<Candidato> candidatos = repository.findAll().stream()
                .filter(c -> c.getStatus().equals(CandidatoStatus.valueOf(situacao)))
                .filter(c -> c.getLocalidade().getCep().equals(cep))
                .collect(Collectors.toCollection(ArrayList::new));

        return candidatos;
    }

    @Override
    public Candidato atualizaCandidato(String codigo, String situacao) throws BadRequestException {
        Candidato candidato = getCandidatoPorCodigo(codigo);
        CandidatoStatus statusAtual = candidato.getStatus();
        CandidatoStatus novoStatus = CandidatoStatus.valueOf(situacao);

        if(novoStatus == statusAtual) throw new BadRequestException();
        else if(novoStatus == CandidatoStatus.PRE_CANDIDATO) throw new BadRequestException();
        else if((statusAtual == CandidatoStatus.PRE_CANDIDATO ||
                statusAtual == CandidatoStatus.INELEGIVEL     ||
                statusAtual == CandidatoStatus.REMOVIDO)      &&
                (novoStatus == CandidatoStatus.ELEITO         ||
                novoStatus == CandidatoStatus.NAO_ELEITO)
        ) throw new BadRequestException();
        else if((statusAtual == CandidatoStatus.ELEGIVEL &&
                novoStatus == CandidatoStatus.REMOVIDO)  ||
                (statusAtual == CandidatoStatus.REMOVIDO &&
                novoStatus == CandidatoStatus.ELEGIVEL)
        ) throw new BadRequestException();
        
        candidato.setStatus(novoStatus);
        repository.save(candidato);

        return candidato;
    }

    @Override
    public Boolean removeCandidato(String codigo) {
        if(!repository.existsByCodigo(codigo)) throw new EntityNotFoundException();

        Candidato candidato = repository.findByCodigo(codigo).orElseThrow(() -> {
            throw new EntityNotFoundException();
        });

        candidato.setStatus(CandidatoStatus.REMOVIDO);
        repository.save(candidato);

        return true;
    }

    @Override
    public Candidato getCandidatoPorCodigo(String codigo) {
        Optional<Candidato> candidato = repository.findByCodigo(codigo);

        if(candidato.isEmpty()) throw new EntityNotFoundException();
        return candidato.get();
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
    public Boolean existPorCodigo(String codigo) {
        return repository.existsByCodigo(codigo);
    }

}
