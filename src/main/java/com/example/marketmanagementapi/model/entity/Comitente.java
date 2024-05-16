package com.example.marketmanagementapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "comitentes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comitente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comitente_id")
    @JsonProperty("id")
    private Long id;

    private String descripcion;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "comitentes_x_mercados",
            joinColumns = @JoinColumn(name = "comitente_id", referencedColumnName = "comitente_id"),
            inverseJoinColumns = @JoinColumn(name = "mercado_id", referencedColumnName = "mercado_id")
    )
    @JsonIgnoreProperties("comitentes")
    private List<Mercado> mercados;

    public void addMercado(Mercado mercado) {
        if (mercados != null) {
            this.mercados.add(mercado);
            mercado.getComitentes().add(this);
        }
    }

    public void removeMercado(Mercado mercado) {
        if (mercados != null) {
            this.mercados.remove(mercado);
            mercado.getComitentes().remove(this);
        }
    }

}
