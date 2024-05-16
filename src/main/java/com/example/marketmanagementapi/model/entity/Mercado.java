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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="mercados")
        public class Mercado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mercado_id")
    @JsonProperty("id")
    private Long mercadoId;

    @Column(name = "codigo")
    private  String mercadoCodigo;

    private String descripcion;

    private String pais;


    @ManyToMany(mappedBy = "mercados", cascade = CascadeType.MERGE)
    @JsonIgnoreProperties("mercados")
    private List<Comitente> comitentes;

    public void addComitente(Comitente comitente) {
        if (this.comitentes != null) {
            this.comitentes.add(comitente);
            comitente.getMercados().add(this);
        }
    }
    public void removeComitente(Comitente comitente) {
        if (this.comitentes != null) {
            this.comitentes.remove(comitente);
            comitente.getMercados().remove(this);
        }
    }

}
