package com.weatherInformation;

import com.weatherInformation.model.WeatherResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for the Weather Information Service.
 * These test cases verify the end-to-end behavior of the weather API.
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeatherIntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getUrl(String path) {
        return "http://localhost:" + port + path;
    }

    @Test
    void shouldFetchCityFromSecondaryApiWhenNotInMemory() {
        ResponseEntity<WeatherResponse> response =
                restTemplate.getForEntity(getUrl("/api/weather/hamilton"), WeatherResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getCity()).isEqualTo("Hamilton");
    }

    @Test
    void shouldReturn203WhenCityNotFoundAnywhere() {
        ResponseEntity<String> response =
                restTemplate.getForEntity(getUrl("/api/weather/napier"), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        assertThat(response.getBody()).contains("City not found");
    }

    @Test
    void shouldReturn400ForEmptyCityInput() {
        ResponseEntity<String> response =
                restTemplate.getForEntity(getUrl("/api/weather"), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).contains("City name must be provided in the URL path");
    }
}

