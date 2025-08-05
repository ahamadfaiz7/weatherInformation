package com.weatherInformation.service;

import com.weatherInformation.model.WeatherResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WeatherData {

    private final Map<String, WeatherResponse> weatherResponseMapData = new ConcurrentHashMap<>();

    @PostConstruct
    public void WeatherData() {
        weatherResponseMapData.put("auckland", new WeatherResponse("Auckland", "24", "C", "23/10/2023", "cloudy"));
        weatherResponseMapData.put("wellington", new WeatherResponse("Wellington", "20", "C", "23/10/2023", "sunny"));
        weatherResponseMapData.put("christchurch", new WeatherResponse("Christchurch", "18", "C", "23/10/2023", "rainy"));
    }

    public WeatherResponse fetchWeather(String city) {
        return weatherResponseMapData.get(city.toLowerCase());
    }
}
