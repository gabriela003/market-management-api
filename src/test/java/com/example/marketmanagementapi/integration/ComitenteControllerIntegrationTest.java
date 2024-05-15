package com.example.marketmanagementapi.integration;

import com.example.marketmanagementapi.model.dto.ComitenteDto;
import com.example.marketmanagementapi.model.dto.MercadoDto;
import com.example.marketmanagementapi.service.ComitenteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ComitenteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ComitenteService comitenteService;

    @Autowired
    private ObjectMapper objectMapper;

    private ComitenteDto comitenteDto;

    @BeforeEach
    public void setup() {
        MercadoDto mercado = new MercadoDto();
        mercado.setMercadoId(1L);
        mercado.setMercadoCodigo("Mercado de Prueba");

        comitenteDto = new ComitenteDto();
        comitenteDto.setId(1L);
        comitenteDto.setDescripcion("Descripción de Prueba");
        comitenteDto.setMercados(Collections.singletonList(mercado));

        Mockito.when(comitenteService.createComitente(Mockito.any(ComitenteDto.class)))
                .thenReturn(comitenteDto);
    }

    @Test
    public void testAddComitente() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/comitente/add-comitente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comitenteDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(comitenteDto.getId()))
                .andExpect(jsonPath("$.descripcion").value(comitenteDto.getDescripcion()))
                .andExpect(jsonPath("$.mercados[0].mercadoCodigo").value("Mercado de Prueba"))
                .andReturn();

        assertThat(mvcResult.getResponse().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);
    }
}


