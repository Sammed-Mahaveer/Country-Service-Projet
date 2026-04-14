# Country-Service-Projet
This project is used to get the country details based on the country code.

Country Service – Spring Boot Project

# Overview

This project is a Spring Boot microservice project that fetches country information from a public API and applies business logic before returning a simplified response.

It demonstrates:

* Clean architecture (Controller → Service → Client → Mapper)
* External API integration using RestTemplate
* Proper error handling
* Unit and controller testing

---

# API Endpoint

# Get Country Details

```
GET /countries/{code}
```

# Example

```
GET http://localhost:8080/countries/US
```

# External API Used

```
https://restcountries.com/v3.1/alpha/{code}
```

* No authentication required
* Returns detailed country data
* Response is mapped to a simplified custom DTO


## Sample Response

```json
{
  "countryCode": "US",
  "name": "United States of America",
  "capital": "Washington, D.C.",
  "region": "Americas",
  "currencies": ["USD"],
  "languages": ["English"],
  "borders": ["CAN", "MEX"],
  "sizeCategory": "LARGE"
}
```

---
# Business Logic

The `sizeCategory` field is derived from population:

| Population Range       | Category |
| ---------------------- | -------- |
| < 1,000,000            | SMALL    |
| 1,000,000 – 10,000,000 | MEDIUM   |
| > 10,000,000           | LARGE    |

---

## Error Handling

| Status Code | Scenario                    |
| ----------- | --------------------------- |
| 400         | Invalid country code format |
| 404         | Country not found           |
| 502         | External API failure        |

---

## Project Structure

```
com.example.countryservice
│
├── controller       → REST endpoints
├── service          → Business logic
├── client           → External API call (RestTemplate)
├── mapper           → Data transformation
├── model            → DTOs
├── exception        → Custom exceptions & handler
└── test             → Unit & controller tests
```

---

## Technologies Used

* Java 21
* Spring Boot (Spring MVC)
* RestTemplate (for external API calls)
* Lombok
* JUnit 5 + Mockito
* MockMvc (for controller testing)

---

Application will start on:

```
http://localhost:8080
```

---

## Design Decisions

### Why RestTemplate?

Since the application follows a synchronous request-response model, RestTemplate was chosen for simplicity and readability.


---

# Why Mapper Layer?

A dedicated mapper ensures:

* Separation of concerns
* Cleaner transformation logic
* Easy extensibility for future fields

---

# Defensive Programming

Since data comes from an external API:

* Null checks are implemented
* Safe defaults (e.g., `"N/A"`, empty lists)
* Prevents runtime failures

---

# Extensibility

The design allows easy extension:

* Add caching (`@Cacheable`)
* Add resilience (`Resilience4j`)
* Add new response fields without breaking structure

---

# Future Improvements

* Add Resilience4j
* Add Caching
* Add Swagger (OpenAPI documentation)



---

## Note

This project focuses on writing clean, maintainable, and production-ready code while keeping the implementation simple and easy to understand.

---
