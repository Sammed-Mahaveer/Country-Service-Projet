package com.example.countryservice.mapper;

import com.example.countryservice.model.CountryResponse;
import com.example.countryservice.model.ExternalCountryResponse;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {

    public CountryResponse toResponse(ExternalCountryResponse ext) {
        return CountryResponse.builder()
                .countryCode(ext.getCca2())
                .name(ext.getName().getCommon())
                .capital(ext.getCapital().get(0))
                .region(ext.getRegion())
                .currencies(ext.getCurrencies().keySet().stream().toList())
                .languages(ext.getLanguages().values().stream().toList())
                .borders(ext.getBorders())
                .sizeCategory(getSizeCategory(ext.getPopulation()))
                .build();
    }

    private String getSizeCategory(long population) {
        if (population < 1_000_000) return "SMALL";
        if (population <= 10_000_000) return "MEDIUM";
        return "LARGE";
    }
}