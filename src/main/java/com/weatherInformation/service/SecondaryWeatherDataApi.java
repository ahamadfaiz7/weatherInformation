package com.weatherInformation.service;

import com.weatherInformation.model.WeatherResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SecondaryWeatherDataApi {

    private final Map<String, WeatherResponse> weatherResponseMapData = new HashMap<>();

    @PostConstruct
    public void WeatherData() {
        weatherResponseMapData.put("auckland", new WeatherResponse("Auckland", "24", "C", "20/10/2024", "cloudy"));
        weatherResponseMapData.put("wellington", new WeatherResponse("Wellington", "20", "C", "20/10/2024", "sunny"));
        weatherResponseMapData.put("christchurch", new WeatherResponse("Christchurch", "18", "C", "20/10/2024", "rainy"));
        weatherResponseMapData.put("hamilton", new WeatherResponse("Hamilton", "25", "C", "20/10/2024", "heavy rain"));
        weatherResponseMapData.put("queenstown", new WeatherResponse("Queenstown", "08", "C", "20/10/2024", "partially cloudy"));
        weatherResponseMapData.put("dunedin", new WeatherResponse("Dunedin", "10", "C", "20/10/2024", "thunderstorms"));
    }

    public WeatherResponse fetchWeather(String city) {
        return weatherResponseMapData.get(city.toLowerCase());
    }
}
