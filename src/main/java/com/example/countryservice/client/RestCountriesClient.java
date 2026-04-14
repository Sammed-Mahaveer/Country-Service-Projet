package com.example.countryservice.client;

import com.example.countryservice.exception.BadGatewayException;
import com.example.countryservice.exception.NotFoundException;
import com.example.countryservice.model.ExternalCountryResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

@Component
public class RestCountriesClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public ExternalCountryResponse getCountry(String code) {
        try {
            ExternalCountryResponse[] response =
                    restTemplate.getForObject(
                            "https://restcountries.com/v3.1/alpha/{code}",
                            ExternalCountryResponse[].class,
                            code
                    );

            if (response == null || response.length == 0) {
                throw new NotFoundException("Country not found");
            }

            return response[0];

        } catch (HttpClientErrorException.NotFound e) {
            throw new NotFoundException("Country not found");
        } catch (Exception e) {
            throw new BadGatewayException("External API failure");
        }
    }
}