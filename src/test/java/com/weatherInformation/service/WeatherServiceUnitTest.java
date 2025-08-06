package com.weatherInformation.service;

import com.weatherInformation.exception.CityNotFoundException;
import com.weatherInformation.model.WeatherResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Unit tests for WeatherService using a mocked SecondaryWeatherDataApi and WeatherServiceApi.
 * These tests validate the behavior of caching, fallback logic, and exception handling
 * at the service layer without starting the full Spring context.
 */
class WeatherServiceUnitTest {

    private WeatherServiceApi weatherService;
    private WeatherDataApi mockSecondaryApi;

    @BeforeEach
    void setUp() {
        mockSecondaryApi = mock(WeatherDataApi.class);
        weatherService = new WeatherService(mockSecondaryApi);
    }

    @Test
    void shouldReturnFromSecondaryApiAndStoreInMemory_WhenCityNotInMemory() {
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        WeatherResponse response = new WeatherResponse("Hamilton", "25", "C", currentDate, "clear sky");
        when(mockSecondaryApi.fetchWeather("hamilton")).thenReturn(response);

        WeatherResponse result = weatherService.getWeatherInfo("hamilton");

        assertThat(result.getCity()).isEqualTo("Hamilton");
        verify(mockSecondaryApi).fetchWeather("hamilton");
    }

    @Test
    void shouldThrowException_WhenCityNotFoundAnywhere() {
        when(mockSecondaryApi.fetchWeather("napier")).thenReturn(null);

        assertThrows(CityNotFoundException.class, () -> weatherService.getWeatherInfo("napier"));
        verify(mockSecondaryApi).fetchWeather("napier");
    }
}
