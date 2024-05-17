package com.example.marketmanagementapi.service.impl;


import com.example.marketmanagementapi.model.dto.Stat;
import com.example.marketmanagementapi.model.dto.StatsByCountry;
import com.example.marketmanagementapi.repository.MercadoRepository;
import com.example.marketmanagementapi.service.MetricasService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;


@Service
public class MetricasServiceImpl implements MetricasService {

    private final MercadoRepository mercadoRepository;

    public MetricasServiceImpl(MercadoRepository mercadoRepository) {
        this.mercadoRepository = mercadoRepository;
    }

    @Override
    public List<StatsByCountry> getCountryStats() {

        List<Object[]> countByMarketAndCountry = mercadoRepository.countComitentesByMarketAndCountry();

        Map<String, Map<String, Float>> percentagesByCountryAndMarket = new HashMap<>();

        int totalComitentesGlobal = countByMarketAndCountry
                .stream()
                .mapToInt(row -> ((Number) row[2]).intValue())
                .sum();

        for (Object[] row : countByMarketAndCountry) {
            String pais = (String) row[0];
            String codigoMercado = (String) row[1];
            int numComitentes = ((Number) row[2]).intValue();
            float porcentaje = ((float) numComitentes / totalComitentesGlobal) * 100;

            percentagesByCountryAndMarket.putIfAbsent(pais, new HashMap<>());

            percentagesByCountryAndMarket.get(pais).put(codigoMercado, porcentaje);
        }

        List<StatsByCountry> result = new ArrayList<>();
        for (Map.Entry<String, Map<String, Float>> entry : percentagesByCountryAndMarket.entrySet()) {
            String pais = entry.getKey();
            Map<String, Float> percentagesByMarket = entry.getValue();

            StatsByCountry statsByCountry = new StatsByCountry();
            statsByCountry.setCountry(pais);

            List<Map<String, Stat>> marketDetails = new ArrayList<>();
            for (Map.Entry<String, Float> marketEntry : percentagesByMarket.entrySet()) {
                String mercado = marketEntry.getKey();
                float porcentaje = marketEntry.getValue();

                Stat stat = new Stat();
                stat.setPercentage(porcentaje);

                Map<String, Stat> marketStat = new HashMap<>();
                marketStat.put(mercado, stat);

                marketDetails.add(marketStat);
            }

            statsByCountry.setMarket(marketDetails);
            result.add(statsByCountry);
        }

        return result;
    }
}
