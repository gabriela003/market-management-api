package com.example.marketmanagementapi.controller;

import com.example.marketmanagementapi.service.MetricasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metricas")
public class MetricasController {

    MetricasService metricasService;

    public MetricasController(MetricasService metricasService) {
        this.metricasService = metricasService;
    }

    @GetMapping("/stats")
    public ResponseEntity<?> getMetricas() {
        return new ResponseEntity<>(metricasService.getCountryStats(), HttpStatus.OK);
    }
}
