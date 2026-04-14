package com.example.countryservice.controller;

import com.example.countryservice.model.CountryResponse;
import com.example.countryservice.service.CountryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CountryController.class)
class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService service;

    @Test
    void shouldReturnCountry() throws Exception {

        CountryResponse response = CountryResponse.builder()
                .countryCode("US")
                .name("United States")
                .build();

        when(service.getCountry("US")).thenReturn(response);

        mockMvc.perform(get("/countries/US"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.countryCode").value("US"));
    }
}