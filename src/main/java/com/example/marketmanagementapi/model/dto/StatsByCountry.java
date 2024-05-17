package com.example.marketmanagementapi.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatsByCountry {

    private String country;
    private List<Map<String, Stat>> market;

}
