package com.weatherInformation.service;

import com.weatherInformation.model.WeatherResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
public class SecondaryWeatherDataApi {

    private final Map<String, WeatherResponse> weatherResponseMapData = new HashMap<>();
    String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));


    @PostConstruct
    public void WeatherData() {
        weatherResponseMapData.put("auckland", new WeatherResponse("Auckland", "24", "C", currentDate, "cloudy"));
        weatherResponseMapData.put("wellington", new WeatherResponse("Wellington", "20", "C", currentDate, "sunny"));
        weatherResponseMapData.put("christchurch", new WeatherResponse("Christchurch", "18", "C", currentDate, "rainy"));
        weatherResponseMapData.put("hamilton", new WeatherResponse("Hamilton", "25", "C", currentDate, "clear sky"));
        weatherResponseMapData.put("queenstown", new WeatherResponse("Queenstown", "08", "C", currentDate, "partially cloudy"));
        weatherResponseMapData.put("dunedin", new WeatherResponse("Dunedin", "10", "C", currentDate, "thunderstorms"));
    }

    public WeatherResponse fetchWeather(String city) {
        return weatherResponseMapData.get(city.toLowerCase());
    }
}
