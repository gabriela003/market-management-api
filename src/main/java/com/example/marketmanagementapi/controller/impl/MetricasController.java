package com.example.marketmanagementapi.controller.impl;

import com.example.marketmanagementapi.controller.MetricasApi;
import com.example.marketmanagementapi.service.MetricasService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metricas")
@Tag( name = "Metricas de Mercado")
public class MetricasController implements MetricasApi {

    MetricasService metricasService;

    public MetricasController(MetricasService metricasService) {
        this.metricasService = metricasService;
    }

    @GetMapping("/stats")
    public ResponseEntity<?> getMetricas() {
        return new ResponseEntity<>(metricasService.getCountryStats(), HttpStatus.OK);
    }
}
