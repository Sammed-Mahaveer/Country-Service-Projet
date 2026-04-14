package com.example.countryservice.service;

import com.example.countryservice.client.RestCountriesClient;
import com.example.countryservice.exception.BadRequestException;
import com.example.countryservice.mapper.CountryMapper;
import com.example.countryservice.model.CountryResponse;
import com.example.countryservice.model.ExternalCountryResponse;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    private final RestCountriesClient client;
    private final CountryMapper mapper;

    public CountryService(RestCountriesClient client, CountryMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    public CountryResponse getCountry(String code) {
        if (!code.matches("^[A-Za-z]{2}$")) {
            throw new BadRequestException("Invalid country code");
        }

        ExternalCountryResponse response = client.getCountry(code.toUpperCase());
        return mapper.toResponse(response);
    }
}