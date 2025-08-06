package com.weatherInformation.service;

import com.weatherInformation.model.WeatherResponse;

public interface WeatherServiceApi {
    WeatherResponse getWeatherInfo(String city);
}
