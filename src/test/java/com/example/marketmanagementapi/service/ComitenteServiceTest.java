package com.example.marketmanagementapi.service;

import com.example.marketmanagementapi.exception.NotFoundException;
import com.example.marketmanagementapi.model.dto.ComitenteDto;
import com.example.marketmanagementapi.model.dto.MercadoDto;
import com.example.marketmanagementapi.model.dto.MessageDto;
import com.example.marketmanagementapi.model.entity.Comitente;
import com.example.marketmanagementapi.model.entity.Mercado;
import com.example.marketmanagementapi.repository.ComitenteRepository;
import com.example.marketmanagementapi.repository.MercadoRepository;
import com.example.marketmanagementapi.service.ComitenteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ComitenteServiceTest {

    @Autowired
    ComitenteService comitenteService;

    @MockBean
    ComitenteRepository comitenteRepository;

    @MockBean
    private MercadoRepository mercadoRepository;

    @MockBean
    private ModelMapper mapper;

    private ComitenteDto comitenteDto;
    private Comitente comitente;
    private Mercado mercado;
    private MercadoDto mercadoDto;
    private final Long comitenteId = 1L;
    private ComitenteDto expectedDto;

    @BeforeEach
    void setUp() {
        comitenteDto = new ComitenteDto();
        comitente = new Comitente();
        mercado = new Mercado();
        mercadoDto = new MercadoDto();
        mercadoDto.setMercadoId(1L);
        comitenteDto.setMercados(List.of(mercadoDto));
        expectedDto = new ComitenteDto();
        expectedDto.setMercados(List.of(mercadoDto));

        when(comitenteRepository.findById(comitenteId)).thenReturn(Optional.of(comitente));
        when(mapper.map(comitente, ComitenteDto.class)).thenReturn(expectedDto);
        when(mapper.map(comitenteDto, Comitente.class)).thenReturn(comitente);
        when(mapper.map(comitente, ComitenteDto.class)).thenReturn(comitenteDto);
    }

    @Test
    void createComitente_ShouldSaveAndReturnComitente_WhenMercadoExists() {
        when(mercadoRepository.findById(1L)).thenReturn(Optional.of(mercado));
        List<Mercado> mercados = List.of(mercado);
        comitente.setMercados(mercados);

        when(comitenteRepository.save(comitente)).thenReturn(comitente);

        ComitenteDto result = comitenteService.createComitente(comitenteDto);

        assertNotNull(result);
        verify(comitenteRepository).save(comitente);
    }

    @Test
    void createComitente_ShouldThrowNotFoundException_WhenMercadoDoesNotExist() {
        when(mercadoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            comitenteService.createComitente(comitenteDto);
        });
    }

    @Test
    void createComitente_ShouldHandleEmptyMercadosSet() {
        comitenteDto.setMercados(Collections.emptyList());

        when(comitenteRepository.save(comitente)).thenReturn(comitente);

        assertDoesNotThrow(() -> comitenteService.createComitente(comitenteDto));

        verify(comitenteRepository).save(comitente);
    }

    @Test
    void createComitente_ShouldHandleNullMercados() {
        comitenteDto.setMercados(null);
        comitente.setMercados(null);

        when(comitenteRepository.save(comitente)).thenReturn(comitente);

        assertDoesNotThrow(() -> comitenteService.createComitente(comitenteDto));

        verify(comitenteRepository).save(comitente);
    }
    @Test
    void getComitente_ExistingId_ReturnsComitenteDto() {
       ComitenteDto actualDto = comitenteService.getComitente(comitenteId);

        assertNotNull(actualDto);
        assertEquals(expectedDto, actualDto);
    }

    @Test
    void getComitente_NonExistingId_ThrowsNotFoundException() {
        Long wrongId = 2L;
        when(comitenteRepository.findById(wrongId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> comitenteService.getComitente(wrongId));
    }

    @Test
    void getComitente_NullId_ThrowsIllegalArgumentException() {

        assertThrows(NotFoundException.class, ()-> comitenteService.getComitente(null));
    }
    @Test
    void testDeleteComitente_WhenComitenteExists() {

        Long comitenteId = 1L;
        Comitente comitente = new Comitente();
        comitente.setId(comitenteId);

        when(comitenteRepository.findById(comitenteId)).thenReturn(Optional.of(comitente));


        MessageDto result = comitenteService.deleteComitente(comitenteId);

        assertNotNull(result);
        assertEquals("Comitente con ID " + comitenteId + " eliminado correctamente", result.getMessage());
        verify(comitenteRepository, times(1)).deleteById(comitenteId);
    }

    @Test
    void testDeleteComitente_WhenComitenteDoesNotExist() {

        Long comitenteId = 1L;

        when(comitenteRepository.findById(comitenteId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> comitenteService.deleteComitente(comitenteId));
        assertEquals("Comitente con ID: " + comitenteId + " no encontrado", exception.getMessage());

        verify(comitenteRepository, never()).deleteById(anyLong());
    }

    @Test
    void testGetAllComitentes() {
        // Arrange
        List<Comitente> comitentes = new ArrayList<>();
        Comitente comitente1 = new Comitente();
        comitente1.setId(1L);
        Comitente comitente2 = new Comitente();
        comitente2.setId(2L);
        comitentes.add(comitente1);
        comitentes.add(comitente2);

        when(comitenteRepository.findAll()).thenReturn(comitentes);


        when(mapper.map(any(Comitente.class), eq(ComitenteDto.class)))
                .thenAnswer(invocation -> {
                    Comitente comitente = invocation.getArgument(0);
                    ComitenteDto comitenteDto = new ComitenteDto();
                    comitenteDto.setId(comitente.getId());
                    comitenteDto.setMercados(List.of(mercadoDto));
                    comitenteDto.setDescripcion(comitente.getDescripcion());
                    return comitenteDto;
                });


        List<ComitenteDto> result = comitenteService.getAllComitentes();

        assertNotNull(result);
        assertEquals(comitentes.size(), result.size());

        verify(comitenteRepository, times(1)).findAll();
        verify(mapper, times(comitentes.size())).map(any(Comitente.class), eq(ComitenteDto.class));
    }

}
