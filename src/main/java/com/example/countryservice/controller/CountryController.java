package com.example.countryservice.controller;

import com.example.countryservice.model.CountryResponse;
import com.example.countryservice.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/countries")
public class CountryController {

    private final CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping("/{code}")
    public ResponseEntity<CountryResponse> getCountry(@PathVariable String code) {
        CountryResponse countryResponse = service.getCountry(code);
        return ResponseEntity.status(HttpStatus.OK).body(countryResponse);
    }
}