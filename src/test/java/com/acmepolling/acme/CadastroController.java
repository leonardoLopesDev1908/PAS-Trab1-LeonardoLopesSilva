package com.acmepolling.acme;

import com.acmepolling.acme.common.ApiConstants;
import com.acmepolling.acme.controller.CadastroController;
import com.acmepolling.acme.controller.dto.request.CreateCandidatoDto;
import com.acmepolling.acme.controller.dto.request.RemoveCandidatoDto;
import com.acmepolling.acme.controller.dto.response.ResponseCandidatoDto;
import com.acmepolling.acme.entity.Candidato;
import com.acmepolling.acme.controller.mapper.CandidatoMapper;
import com.acmepolling.acme.entity.enums.CandidatoSituacao;
import com.acmepolling.acme.service.candidato.CandidatoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CadastroController.class)
class CadastroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CandidatoService service;

    @MockBean
    private CandidatoMapper mapper;

    @Test
    @DisplayName("Deve retornar lista de todos os candidatos")
    void getTodos_DeveRetornarListaDeCandidatos() throws Exception {
        Candidato candidato = new Candidato();

        ResponseCandidatoDto responseDto = new ResponseCandidatoDto(
                "João", CandidatoSituacao.PRE_CANDIDATO,
                "25", "PARTIDO", "94935050", "Cachoeirinha",
                "RS"
        );

        when(service.getTodos()).thenReturn(new ArrayList<>(List.of(candidato)));
        when(mapper.toDto(candidato)).thenReturn(responseDto);

        mockMvc.perform(get(ApiConstants.API_CADASTRO + "/listacandidatos")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].nome").value("João"));

        verify(service).getTodos();
    }

    @Test
    @DisplayName("Deve filtrar candidatos por CEP e situação")
    void getTodosPorLocalSituacao_DeveRetornarCandidatosFiltrados() throws Exception {
        String cep = "94935050";
        String situacao = "ELEGIVEL";
        Candidato candidato = new Candidato();
        ResponseCandidatoDto responseDto = new ResponseCandidatoDto(
                "Maria", CandidatoSituacao.ELEGIVEL,
                "99", "PARTIDO", cep, "Cachoeirinha",
                "RS"
        );

        when(service.getTodosPorLocalSituacao(cep, situacao)).thenReturn(new ArrayList<>(List.of(candidato)));
        when(mapper.toDto(candidato)).thenReturn(responseDto);

        mockMvc.perform(get(ApiConstants.API_CADASTRO + "/listacandidatoslocalidade/{cep}/situacao/{situacao}", cep, situacao)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service).getTodosPorLocalSituacao(cep, situacao);
    }

    @Test
    @DisplayName("Deve cadastrar candidato com sucesso")
    void cadastrarCandidato_DeveRetornarTrue() throws Exception {
        CreateCandidatoDto createDto = new CreateCandidatoDto("Carlos", "11", "94970530");
        Candidato candidato = new Candidato();

        when(mapper.toEntity(any(CreateCandidatoDto.class))).thenReturn(candidato);
        when(service.cadastrarCandidato(candidato)).thenReturn(true);

        mockMvc.perform(post(ApiConstants.API_CADASTRO + "/cadcandidato")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(service).cadastrarCandidato(candidato);
    }

    @Test
    @DisplayName("Deve atualizar situação do candidato")
    void atualizaCandidato_DeveRetornarCandidatoAtualizado() throws Exception {
        String numero = "123";
        String cep = "94935050";
        String novaSituacao = "ELEGIVEL";
        Candidato candidato = new Candidato();
        ResponseCandidatoDto responseDto = new ResponseCandidatoDto(
                "Carlos", CandidatoSituacao.ELEGIVEL,
                numero, "PARTIDO", "90010170", "Cachoeirinha",
                "RS"
        );

        when(service.atualizaCandidato(numero, cep, novaSituacao)).thenReturn(candidato);
        when(mapper.toDto(candidato)).thenReturn(responseDto);

        mockMvc.perform(put(ApiConstants.API_CADASTRO + "/atualizandidato/{numero}/{cep}/situacao/{situacao}", numero, cep, novaSituacao)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numero").value(numero))
                .andExpect(jsonPath("$.status").value(novaSituacao));

        verify(service).atualizaCandidato(numero, cep, novaSituacao);
    }

    @Test
    @DisplayName("Deve lançar BadRequestException quando a atualização falhar")
    void atualizaCandidato_QuandoServiceLançaExceção_DeveRetornarErro() throws Exception {
        String cep = "94935050";
        String numero = "999";
        String situacao = "INVALIDA";

        when(service.atualizaCandidato(numero, cep, situacao))
                .thenThrow(new BadRequestException("Situação inválida"));

        mockMvc.perform(put(ApiConstants.API_CADASTRO + "/atualizandidato/{numero}/{cep}/situacao/{situacao}", numero, cep, situacao))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve remover candidato via body")
    void removeCandidato_DeveRetornarTrue() throws Exception {
        String cep = "94935050";
        RemoveCandidatoDto removeDto = new RemoveCandidatoDto("123", cep);

        when(service.removeCandidato("123", cep)).thenReturn(true);

        mockMvc.perform(delete(ApiConstants.API_CADASTRO + "/removecandidato")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(removeDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(service).removeCandidato("123", cep);
    }
}