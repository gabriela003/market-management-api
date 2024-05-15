package com.example.marketmanagementapi.service.impl;

import com.example.marketmanagementapi.exception.NotFoundException;
import com.example.marketmanagementapi.model.dto.ComitenteDto;
import com.example.marketmanagementapi.model.dto.MessageDto;
import com.example.marketmanagementapi.model.entity.Comitente;
import com.example.marketmanagementapi.model.entity.Mercado;
import com.example.marketmanagementapi.repository.ComitenteRepository;
import com.example.marketmanagementapi.repository.MercadoRepository;
import com.example.marketmanagementapi.service.ComitenteService;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ComitenteServiceImpl implements ComitenteService {


    private final ComitenteRepository comitenteRepository;

    private final ModelMapper mapper;
    private final MercadoRepository mercadoRepository;

    public ComitenteServiceImpl(ComitenteRepository comitenteRepository, ModelMapper mapper, MercadoRepository mercadoRepository) {
        this.comitenteRepository = comitenteRepository;
        this.mapper = mapper;
        this.mercadoRepository = mercadoRepository;
    }

    @Override
    public ComitenteDto createComitente(ComitenteDto newComitente) {
        Comitente comitenteToSave = mapper.map(newComitente, Comitente.class);
        if(newComitente.getMercados() != null && !newComitente.getMercados().isEmpty()) {
            Set<Mercado> mercados = newComitente.getMercados().stream()
                    .map(
                            mercadoDto -> mercadoRepository.findById(mercadoDto.getMercadoId())
                                    .orElseThrow(() -> new NotFoundException("Mercado no encontrado"))
                    ).collect(Collectors.toSet());

            comitenteToSave.setMercados(new ArrayList<>(mercados));
        }else{
            comitenteToSave.setMercados(new ArrayList<>());
        }
        comitenteToSave = comitenteRepository.save(comitenteToSave);
        return mapper.map(comitenteToSave, ComitenteDto.class);

    }

    @Override
    public ComitenteDto getComitente(Long comitenteId) {
        Comitente com = comitenteRepository.findById(comitenteId)
                .orElseThrow(()-> new NotFoundException("Comitente no encontrado"));

        return mapper.map(com, ComitenteDto.class);
    }

    @Override
    public ComitenteDto updateComitente(ComitenteDto comitenteToUpdate) {
return null;
    }

    @Override
    public MessageDto deleteComitente(Long comitenteId) {

        Optional<Comitente> comitenteToDelete = comitenteRepository.findById(comitenteId);

        if (comitenteToDelete.isPresent()) {
            comitenteRepository.deleteById(comitenteId);
            MessageDto messageDto = new MessageDto();
            messageDto.setMessage("Comitente con ID " + comitenteId + " eliminado correctamente");
            return messageDto;
        } else {
            throw new NotFoundException("Comitente con ID: " + comitenteId +" no encontrado");
        }
    }

    @Override
    public List<ComitenteDto> getAllComitentes() {
        List<Comitente> comitentes = comitenteRepository.findAll();
        List<ComitenteDto> comitenteDtos = new ArrayList<>();
        comitentes.forEach(com -> comitenteDtos.add(mapper.map(com, ComitenteDto.class)));
        return comitenteDtos;
    }
}
