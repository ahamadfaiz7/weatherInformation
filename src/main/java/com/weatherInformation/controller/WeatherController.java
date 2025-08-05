package com.weatherInformation.controller;

import com.weatherInformation.exception.InvalidInputException;
import com.weatherInformation.model.WeatherResponse;
import com.weatherInformation.service.WeatherService;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/{city}")
    public WeatherResponse getWeather(@PathVariable String city) {
        return weatherService.getWeatherInfo(city);
    }

    @GetMapping
    public WeatherResponse getWeatherInfoWithoutCity() {
        throw new InvalidInputException("City name must be provided in the URL path");
    }

}
