package com.weatherInformation.service;

import com.weatherInformation.model.WeatherResponse;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private final WeatherData weatherData;

    public WeatherService(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

    public WeatherResponse getWeather(String city) {
        city = city.toLowerCase();

        WeatherResponse response = weatherData.fetchWeather(city);
        if (response == null) {
            //throw new Exception("City not found: " + city);
        }

        return response;
    }
}
