package com.example.marketmanagementapi.service;

import com.example.marketmanagementapi.model.dto.StatsByCountry;

import java.util.List;

public interface MetricasService {

    List<StatsByCountry> getCountryStats();
}
