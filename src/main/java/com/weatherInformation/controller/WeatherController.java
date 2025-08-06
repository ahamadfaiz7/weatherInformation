package com.weatherInformation.controller;

import com.weatherInformation.exception.InvalidInputException;
import com.weatherInformation.model.WeatherResponse;
import com.weatherInformation.service.WeatherService;
import com.weatherInformation.service.WeatherServiceApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller that handles weather info related endpoints.
 */
@RestController
@RequestMapping("/api/weather")
@Tag(name = "Weather API", description = "Provides weather information by city")

public class WeatherController {

    private final WeatherServiceApi weatherService;

    public WeatherController(WeatherServiceApi weatherService) {
        this.weatherService = weatherService;
    }

    /**
     * Retrieves weather info for a given city.
     *
     * @param city the name of the city
     * @return weather information for the city
     */
    @GetMapping("/{city}")
    @Operation(summary = "Get weather by city", description = "Returns weather details for the specified city")
    public WeatherResponse getWeather(@PathVariable String city) {
        return weatherService.getWeatherInfo(city);
    }

    /**
     * If no city is passed.
     *
     * @return Invalid request Exception
     */
    @GetMapping
    @Operation(summary = "Invalid request", description = "Throws an error if city is not provided")
    public WeatherResponse getWeatherInfoWithoutCity() {
        throw new InvalidInputException("City name must be provided in the URL path");
    }

}
