package com.weatherInformation.model;

import java.io.Serializable;

public class WeatherResponse implements Serializable {
    private String city;
    private String temp;
    private String unit;
    private String date;
    private String weather;

    public WeatherResponse(String city, String temp, String unit, String date, String weather) {
        this.city = city;
        this.temp = temp;
        this.unit = unit;
        this.date = date;
        this.weather = weather;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}