package com.weatherInformation.service;

import com.weatherInformation.model.WeatherResponse;

public interface WeatherDataApi {
    WeatherResponse fetchWeather(String city);
}
