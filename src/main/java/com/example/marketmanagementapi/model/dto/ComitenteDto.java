package com.example.marketmanagementapi.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComitenteDto {
    private Long id;
    private String descripcion;
    @JsonIgnoreProperties("comitentes")
    private List<MercadoDto> mercados;

}
