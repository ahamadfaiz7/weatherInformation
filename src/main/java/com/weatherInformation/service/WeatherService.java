package com.weatherInformation.service;

import com.weatherInformation.exception.CityNotFoundException;
import com.weatherInformation.model.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service

public class WeatherService {

    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);
    private final Map<String, WeatherResponse> inMemoryWeatherData = new ConcurrentHashMap<>();
    private final SecondaryWeatherDataApi secondaryWeatherDataApi;

    public WeatherService(SecondaryWeatherDataApi secondaryWeatherDataApi) {
        this.secondaryWeatherDataApi = secondaryWeatherDataApi;
    }

    public WeatherResponse getWeatherInfo(String city) {
        if (log.isInfoEnabled()) {
            log.info("fetching results for the city : {}", city);
        }
        city = city.toLowerCase();
        if (inMemoryWeatherData.containsKey(city)) {
            if (log.isInfoEnabled()) {
                log.info("Requested city is found in the dataset  : {}", city);
            }
            return inMemoryWeatherData.get(city);
        }

        if (log.isInfoEnabled()) {
            log.info("Requested city is not found in the dataset. Fetching the city info from the external API  : {}", city);
        }
        WeatherResponse response = secondaryWeatherDataApi.fetchWeather(city);
        if (response == null) {
            throw new CityNotFoundException("City not found: " + city);
        }

        /* remove any random key/value from the map if the size of in-memory data feeds are more than 3*/
        if (inMemoryWeatherData.size() >= 3) {
            String keyToRemove = inMemoryWeatherData.keySet().iterator().next();
            if (log.isInfoEnabled()) {
                log.info("Removing the city from dataset : {}", keyToRemove);
            }
            inMemoryWeatherData.remove(keyToRemove);
        }
        inMemoryWeatherData.put(city, response);
        if (log.isInfoEnabled()) {
            log.info("Requested city added to the dataset : {}", city);
        }
        return response;
    }
}
