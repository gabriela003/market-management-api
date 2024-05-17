package com.example.marketmanagementapi.repository;

import com.example.marketmanagementapi.model.entity.Mercado;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MercadoRepository extends JpaRepository<Mercado, Long> {

    @Query("SELECT m.pais, m.mercadoCodigo, COUNT(cm) " +
            "FROM Mercado m " +
            "LEFT JOIN m.comitentes cm " +
            "GROUP BY m.pais, m.mercadoCodigo")
    List<Object[]> countComitentesByMarketAndCountry();
}
