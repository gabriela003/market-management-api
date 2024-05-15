package com.example.marketmanagementapi.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MercadoDto {
    private Long mercadoId;
    private  String mercadoCodigo;
    private String descripcion;
    private String pais;

    @JsonIgnoreProperties("mercados")
    private List<ComitenteDto> comitentes;

}
