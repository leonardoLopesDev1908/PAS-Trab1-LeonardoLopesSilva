package com.acmepolling.acme.controller;

import com.acmepolling.acme.common.ApiConstants;
import com.acmepolling.acme.controller.mapper.CandidatoMapper;
import com.acmepolling.acme.controller.dto.request.ResponseCandidatoVotos;
import com.acmepolling.acme.controller.dto.response.ResponseCandidatoDto;
import com.acmepolling.acme.controller.dto.response.ResponseLocalidadeDto;
import com.acmepolling.acme.controller.dto.response.ResponseMaisVotadosDto;
import com.acmepolling.acme.service.apuracao.ApuracaoService;
import com.acmepolling.acme.service.candidato.CandidatoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.API_APURACAO)
public class ApuracaoController {

    private final ApuracaoService service;

    private final CandidatoService candidatoService;
    private final CandidatoMapper candidatoMapper;

    public ApuracaoController(ApuracaoService service, CandidatoService candidatoService,
                            CandidatoMapper candidatoMapper) {
        this.service = service;
        this.candidatoService = candidatoService;
        this.candidatoMapper = candidatoMapper;
    }

    @GetMapping("/consultaeleito")
    public ResponseEntity<ResponseCandidatoDto> consultaEleito(@RequestParam String cep) {
        ResponseCandidatoDto response = service.consultaEleito(cep);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/consultalocalidade")
    public ResponseEntity<ResponseLocalidadeDto> consultaLocalidade(@RequestParam String cep) {
        return ResponseEntity.of(new ResponseLocalidadeDto());
    }

    @GetMapping("/consultaeleito")
    public ResponseEntity<ResponseCandidatoDto> consultaEleito(@RequestParam String cep) {
       return ResponseEntity.ok();
    }

    @GetMapping("/consultalocalidade")
    public ResponseEntity<ResponseLocalidadeDto> consultaLocalidade(@RequestParam String cep) {

    }

    @GetMapping("/listamaisvotadospartido")
    public ResponseEntity<ArrayList<ResponseMaisVotadosDto>> consultaMaisVotados() {
        ArrayList<ResponseMaisVotadosDto> maisVotados = service.maisVotadosPorPartido().stream()
            .map((k, v) -> new ResponseMaisVotado())
            .toList();

        return ResponseEntity.ok(maisVotados);
    }

    @GetMapping("/consultacandidato")
    public ResponseEntity<ResponseCandidatoVotos> consultaCandidato(@RequestParam String numero) {
        ResponseCandidatoDto dto = mapper.toDto(candidatoService.getCandidatoPorNumero(numero));
        return ResponseEntity.ok(dto);
    }
}
