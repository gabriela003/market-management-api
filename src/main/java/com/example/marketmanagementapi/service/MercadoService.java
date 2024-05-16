package com.example.marketmanagementapi.service;

import com.example.marketmanagementapi.model.dto.MercadoDto;
import com.example.marketmanagementapi.model.dto.MessageDto;

import java.util.List;

public interface MercadoService {
    MessageDto createMercado(MercadoDto newMercado);
    MercadoDto getMercado(Long mercadoId);
    MercadoDto updateMercado(MercadoDto mercadoToUpdate);
    MessageDto deleteMercado(Long mercadoId);

    List<MercadoDto> getAllMercados();
    MessageDto addComitenteToMercado (Long idComitente, Long idMercado);
    MessageDto deleteComitenteFromMercado (Long idComitente, Long idMercado);

}
