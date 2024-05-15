package com.example.marketmanagementapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "comitentes")
@Getter
@Setter
public class Comitente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comitente_id")
    @JsonProperty("id")
    private Long id;

    private String descripcion;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "comitentes_x_mercados",
            joinColumns = @JoinColumn(name = "comitente_id", referencedColumnName = "comitente_id"),
            inverseJoinColumns = @JoinColumn(name = "mercado_id", referencedColumnName = "mercado_id")
    )
    @JsonIgnoreProperties("comitentes")
    private List<Mercado> mercados;

}
