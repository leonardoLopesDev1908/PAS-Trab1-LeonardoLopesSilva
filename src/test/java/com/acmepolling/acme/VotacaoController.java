package com.acmepolling.acme;

import com.acmepolling.acme.common.ApiConstants;
import com.acmepolling.acme.controller.VotacaoController;
import com.acmepolling.acme.controller.dto.request.VotoRequest;
import com.acmepolling.acme.service.voto.VotacaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VotacaoController.class)
class VotacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VotacaoService service;

    @Test
    @DisplayName("Deve cadastrar voto com sucesso e retornar true")
    void cadVoto_DeveCadastrarVotoComSucesso() throws Exception {
        VotoRequest votoRequest = new VotoRequest("25", "94935050");

        when(service.cadVoto("94935050", "25")).thenReturn(true);

        mockMvc.perform(post(ApiConstants.API_VOTACAO + "/cadvoto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(votoRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(service).cadVoto("94935050", "25");
    }

    @Test
    @DisplayName("Deve retornar false quando o serviço não conseguir cadastrar o voto")
    void cadVoto_QuandoFalhar_DeveRetornarFalse() throws Exception {
        VotoRequest votoRequest = new VotoRequest("999", "94935050");

        when(service.cadVoto("94935050", "999")).thenReturn(false);

        mockMvc.perform(post(ApiConstants.API_VOTACAO + "/cadvoto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(votoRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));

        verify(service).cadVoto("94935050", "999");
    }
}