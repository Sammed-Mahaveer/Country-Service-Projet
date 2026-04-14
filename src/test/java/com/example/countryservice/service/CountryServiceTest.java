package com.example.countryservice.service;

import com.example.countryservice.client.RestCountriesClient;
import com.example.countryservice.exception.BadRequestException;
import com.example.countryservice.mapper.CountryMapper;
import com.example.countryservice.model.CountryResponse;
import com.example.countryservice.model.ExternalCountryResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CountryServiceTest {

    private final RestCountriesClient client = mock(RestCountriesClient.class);
    private final CountryMapper mapper = mock(CountryMapper.class);

    private final CountryService service = new CountryService(client, mapper);

    @Test
    void shouldReturnCountryResponse() {

        ExternalCountryResponse external = new ExternalCountryResponse();
        CountryResponse response = CountryResponse.builder().countryCode("US").build();

        when(client.getCountry("US")).thenReturn(external);
        when(mapper.toResponse(external)).thenReturn(response);

        CountryResponse result = service.getCountry("US");

        assertEquals("US", result.getCountryCode());
    }

    @Test
    void shouldThrowBadRequestForInvalidCode() {
        assertThrows(BadRequestException.class,
                () -> service.getCountry("USA"));
    }
}