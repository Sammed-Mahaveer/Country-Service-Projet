package com.example.countryservice.mapper;

import com.example.countryservice.model.ExternalCountryResponse;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CountryMapperTest {

    private final CountryMapper mapper = new CountryMapper();

    @Test
    void shouldMapCorrectly() {

        ExternalCountryResponse ext = new ExternalCountryResponse();
        ext.setCca2("IN");

        ExternalCountryResponse.Name name = new ExternalCountryResponse.Name();
        name.setCommon("India");
        ext.setName(name);

        ext.setCapital(List.of("New Delhi"));
        ext.setRegion("Asia");
        ext.setCurrencies(Map.of("INR", Map.of()));
        ext.setLanguages(Map.of("hi", "Hindi"));
        ext.setBorders(List.of("PAK", "CHN"));
        ext.setPopulation(1_400_000_000);

        var response = mapper.toResponse(ext);

        assertEquals("IN", response.getCountryCode());
        assertEquals("India", response.getName());
        assertEquals("LARGE", response.getSizeCategory());
    }


}