package com.example.countryservice.model;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class ExternalCountryResponse {
    private String cca2;
    private Name name;
    private List<String> capital;
    private String region;
    private Map<String, Object> currencies;
    private Map<String, String> languages;
    private List<String> borders;
    private long population;

    @Data
    public static class Name {
        private String common;
    }
}