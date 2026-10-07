package com.acmepolling.acme;

import com.acmepolling.acme.common.ApiConstants;
import com.acmepolling.acme.controller.ApuracaoController;
import com.acmepolling.acme.controller.dto.response.CandidatoVotosCount;
import com.acmepolling.acme.controller.dto.response.ResponseCandidatoDto;
import com.acmepolling.acme.controller.dto.response.ResponseVotosDto;
import com.acmepolling.acme.entity.Candidato;
import com.acmepolling.acme.entity.enums.CandidatoSituacao;
import com.acmepolling.acme.controller.mapper.CandidatoMapper;
import com.acmepolling.acme.service.apuracao.ApuracaoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ApuracaoController.class)
class ApuracaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApuracaoService service;

    @MockBean
    private CandidatoMapper candidatoMapper;

    @Test
    @DisplayName("Deve consultar o candidato eleito por CEP")
    void consultaEleito_DeveRetornarCandidatoEleito() throws Exception {
        String cep = "94935050";
        Candidato candidato = new Candidato();
        ResponseCandidatoDto responseDto = new ResponseCandidatoDto(
                "João", CandidatoSituacao.ELEITO,
                "25", "PARTIDO", cep, "Cachoeirinha", "RS"
        );

        when(service.consultaEleito(cep)).thenReturn(candidato);
        when(candidatoMapper.toDto(candidato)).thenReturn(responseDto);

        mockMvc.perform(get(ApiConstants.API_APURACAO + "/consultaeleito")
                        .param("cep", cep)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João"))
                .andExpect(jsonPath("$.cep").value(cep))
                .andExpect(jsonPath("$.status").value("ELEITO"));

        verify(service).consultaEleito(cep);
        verify(candidatoMapper).toDto(candidato);
    }

    @Test
    @DisplayName("Deve consultar contagem de votos por localidade (CEP)")
    void consultaLocalidade_DeveRetornarListaDeVotosPorCandidato() throws Exception {
        String cep = "94935050";
        CandidatoVotosCount votosCount = new CandidatoVotosCount("João", "25", "Partidos sem Votos", 0L);

        when(service.consultaLocalidade(cep)).thenReturn(List.of(votosCount));

        mockMvc.perform(get(ApiConstants.API_APURACAO + "/consultalocalidade")
                        .param("cep", cep)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].numero").value("25"))
                .andExpect(jsonPath("$[0].nome").value("João"))
                .andExpect(jsonPath("$[0].votos").value(0));

        verify(service).consultaLocalidade(cep);
    }

    @Test
    @DisplayName("Deve consultar quantidade de votos de um candidato específico pelo código/número")
    void consultaCandidato_DeveRetornarVotosDoCandidato() throws Exception {
        String numero = "25";
        ResponseVotosDto responseDto = new ResponseVotosDto(1500L, 600L);

        when(service.consultaVotosCandidato(numero)).thenReturn(responseDto);

        mockMvc.perform(get(ApiConstants.API_APURACAO + "/consultacandidato")
                        .param("numero", numero)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.votosValidos").value(1500))
                .andExpect(jsonPath("$.votosInvalidos").value(600));

        verify(service).consultaVotosCandidato(numero);
    }

    @Test
    @DisplayName("Deve consultar os candidatos mais votados por partido")
    void consultaMaisVotados_DeveRetornarListaDosMaisVotados() throws Exception {
        CandidatoVotosCount candidato1 = new CandidatoVotosCount("João", "25", "Partido partidário", 2000L);
        CandidatoVotosCount candidato2 = new CandidatoVotosCount("Maria", "13", "Partido dos Candidatos", 1670L);

        when(service.maisVotadosPorPartido()).thenReturn(List.of(candidato1, candidato2));

        mockMvc.perform(get(ApiConstants.API_APURACAO + "/listamaisvotadospartido")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].numero").value("25"))
                .andExpect(jsonPath("$[0].votos").value(2000))
                .andExpect(jsonPath("$[1].numero").value("13"))
                .andExpect(jsonPath("$[1].votos").value(1670));

        verify(service).maisVotadosPorPartido();
    }
}