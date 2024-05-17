package com.example.marketmanagementapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface MetricasApi {
    @Operation(summary = "Obtener porcentage de comitentes por mercado.", description = "Endpoint para obtener métricas del mercado, porcentage de cifras totalizadoras de distribución\n" +
            "de comitentes por país y mercado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtuvieron las métricas exitosamente"),
            @ApiResponse(responseCode = "500", description = "Ocurrió un error interno en el servidor")
    })
    @GetMapping("/stats")
    ResponseEntity<?> getMetricas();
}
