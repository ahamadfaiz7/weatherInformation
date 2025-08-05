package com.weatherInformation.service;

import com.weatherInformation.model.WeatherResponse;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service

public class WeatherService {

    private final Map<String, WeatherResponse> inMemoryWeatherData = new ConcurrentHashMap<>();
    private final SecondaryWeatherDataApi secondaryWeatherDataApi;

    public WeatherService(SecondaryWeatherDataApi secondaryWeatherDataApi) {
        this.secondaryWeatherDataApi = secondaryWeatherDataApi;
    }

    public WeatherResponse getWeatherInfo(String city) {
        city = city.toLowerCase();
        if (inMemoryWeatherData.containsKey(city)) {
            return inMemoryWeatherData.get(city);
        }
        WeatherResponse response = secondaryWeatherDataApi.fetchWeather(city);
        if (response == null) {
            /*throw new Exception("City not found: " + city);*/
        }
        //Will remove any random key/value from the concurrent hash map if the size of in-memory feeds are more than 3
        if (inMemoryWeatherData.size() >= 3) {
            String keyToRemove = inMemoryWeatherData.keySet().iterator().next();
            inMemoryWeatherData.remove(keyToRemove);
        }
        inMemoryWeatherData.put(city, response);
        return response;
    }
}
