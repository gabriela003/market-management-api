package com.example.marketmanagementapi.service.impl;

import com.example.marketmanagementapi.enums.Pais;
import com.example.marketmanagementapi.exception.NotFoundException;
import com.example.marketmanagementapi.model.dto.MercadoDto;
import com.example.marketmanagementapi.model.dto.MessageDto;
import com.example.marketmanagementapi.model.entity.Comitente;
import com.example.marketmanagementapi.model.entity.Mercado;
import com.example.marketmanagementapi.repository.ComitenteRepository;
import com.example.marketmanagementapi.repository.MercadoRepository;
import com.example.marketmanagementapi.service.MercadoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MercadoServiceImpl implements MercadoService {

    private final ModelMapper mapper;
    private final MercadoRepository mercadoRepository;
    private final ComitenteRepository comitenteRepository;

    public MercadoServiceImpl(ModelMapper mapper, MercadoRepository mercadoRepository, ComitenteRepository comitenteRepository) {
        this.mapper = mapper;
        this.mercadoRepository = mercadoRepository;
        this.comitenteRepository = comitenteRepository;
    }

    @Override
    @Transactional
    public MessageDto createMercado(MercadoDto newMercado) {
        if(newMercado.getPais() == null ){
            throw new NotFoundException("Debe enviarse un pais para operar en el mercado");
        }
        String pais = newMercado.getPais();

        if ((!pais.equalsIgnoreCase(Pais.ARGENTINA.name()) && !pais.equalsIgnoreCase(Pais.URUGUAY.name()))) {
            throw new NotFoundException("El país no es válido");
        }

        Mercado mercadoToSave = mapper.map(newMercado, Mercado.class);

        if (newMercado.getComitentes() != null && !newMercado.getComitentes().isEmpty()) {
            List<Comitente> comitentes = newMercado.getComitentes().stream()
                    .map(
                            dto -> comitenteRepository.findById(dto.getId())
                            .orElseThrow(() -> new NotFoundException("Comitente con ID " + dto.getId() + " no existe"))
                    )
                    .collect(Collectors.toList());

            comitentes.forEach(mercadoToSave::addComitente);
        }
        Mercado savedMercado = mercadoRepository.save(mercadoToSave);

        MessageDto response = new MessageDto();

        response.setMessage("El mercado con id " + savedMercado.getMercadoId() + " se ha creado correctamente");

        return response;
    }

    @Override
    public MercadoDto getMercado(Long mercadoId) {
        Mercado mercado = mercadoRepository.findById(mercadoId)
                .orElseThrow(() -> new NotFoundException("Mercado no encontrado"));
        return mapper.map(mercado, MercadoDto.class);

    }

    @Override
    public MercadoDto updateMercado(MercadoDto mercadoToUpdate) {
        return null;
    }

    @Override
    public MessageDto deleteMercado(Long mercadoId) {
        Mercado mercadoToDelete = mercadoRepository.findById(mercadoId)
                        .orElseThrow(()-> new NotFoundException("Mercado no encontrado"));

        mercadoToDelete.getComitentes().forEach(comitente -> comitente.getMercados().remove(mercadoToDelete));
        mercadoToDelete.getComitentes().clear();

        mercadoRepository.deleteById(mercadoId);

        MessageDto response = new MessageDto();
        response.setMessage("El mercado con id "+ mercadoId +" se ha eliminado correctamente");
        return response;
    }

    @Override
    public List<MercadoDto> getAllMercados() {
        List<Mercado> allMercados = mercadoRepository.findAll();
        List<MercadoDto> mercadoDtos = new ArrayList<>();
        allMercados.forEach(mercado -> {
            mercadoDtos.add(mapper.map(mercado, MercadoDto.class));
        });
        return mercadoDtos;
    }

    @Override
    public MessageDto addComitenteToMercado(Long comitenteId, Long mercadoId) {
        Mercado mercado = mercadoRepository.findById(mercadoId)
                .orElseThrow(() -> new NotFoundException("Mercado con ID " + mercadoId + " no encontrado"));

        Comitente comitente = comitenteRepository.findById(comitenteId)
                .orElseThrow(() -> new NotFoundException("Comitente con ID " + comitenteId + " no encontrado"));

        if (!mercado.getComitentes().contains(comitente)) {
            mercado.addComitente(comitente);
            mercadoRepository.save(mercado);
        }
        String salida = "Comitente " + comitenteId + " agregado a mercado "+ mercadoId +" de forma exitosa";
        return new MessageDto(salida, 200);
    }

    @Override
    public MessageDto deleteComitenteFromMercado(Long idComitente, Long idMercado) {
        Mercado mercado = mercadoRepository.findById(idMercado)
                .orElseThrow(() -> new NotFoundException("Mercado con ID " + idMercado + " no encontrado"));

        Comitente comitente = comitenteRepository.findById(idComitente)
                .orElseThrow(() -> new NotFoundException("Comitente con ID " + idComitente + " no encontrado"));

        if (mercado.getComitentes().contains(comitente)) {
            mercado.removeComitente(comitente);
            mercadoRepository.save(mercado);
        }
        String salida = "Comitente " + idComitente + " eliminado de mercado "+ idMercado +" de forma exitosa";
        return new MessageDto(salida, 200);

    }
}
