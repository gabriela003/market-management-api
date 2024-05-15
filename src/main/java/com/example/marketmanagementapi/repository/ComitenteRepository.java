package com.example.marketmanagementapi.repository;

import com.example.marketmanagementapi.model.entity.Comitente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComitenteRepository extends JpaRepository<Comitente, Long> {
}
