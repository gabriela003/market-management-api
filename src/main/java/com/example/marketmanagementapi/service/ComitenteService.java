package com.example.marketmanagementapi.service;

import com.example.marketmanagementapi.model.dto.ComitenteDto;
import com.example.marketmanagementapi.model.dto.MessageDto;

import java.util.List;

public interface ComitenteService {
    ComitenteDto createComitente(ComitenteDto newComitente);
    ComitenteDto getComitente(Long comitenteId);
    ComitenteDto updateComitente(ComitenteDto comitenteToUpdate);
    MessageDto deleteComitente(Long comitenteId);

    List<ComitenteDto> getAllComitentes();

}
