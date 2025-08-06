package com.weatherInformation.service;

import com.weatherInformation.exception.CityNotFoundException;
import com.weatherInformation.model.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service layer that handles business logic for retrieving weather
 * data and adding to in memory dataset map.
 */
@Service
public class WeatherService implements WeatherServiceApi{

    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);
    private final Map<String, WeatherResponse> inMemoryWeatherData = new ConcurrentHashMap<>();
    private final WeatherDataApi secondaryWeatherDataApi;

    public WeatherService(WeatherDataApi secondaryWeatherDataApi) {
        this.secondaryWeatherDataApi = secondaryWeatherDataApi;
    }

    /**
     * Retrieves weather data response for a city, first from the in memory store, then from secondary API.
     * Automatically removes one entry if the in-memory dataset exceeds 3 cities.
     * If the weather info is not found for the requested city then it will throw exception.
     *
     * @param city
     * @return WeatherResponse
     * @throws CityNotFoundException
     */
    public WeatherResponse getWeatherInfo(String city) {
        if (log.isInfoEnabled()) {
            log.info("fetching results for the city : {}", city);
        }
        city = city.toLowerCase();
        if (inMemoryWeatherData.containsKey(city)) {
            if (log.isInfoEnabled()) {
                log.info("Requested city is found in the in-memory dataset  : {}", city);
            }
            return inMemoryWeatherData.get(city);
        }

        if (log.isInfoEnabled()) {
            log.info("Requested city is not found in the in-memory dataset. Fetching the city info from the external API  : {}", city);
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
            log.info("Requested city added to the in-memory dataset : {}", city);
        }
        return response;
    }
}
