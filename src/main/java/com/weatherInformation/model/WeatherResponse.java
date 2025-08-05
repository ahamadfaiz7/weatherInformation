    package com.weatherInformation.model;

    import lombok.Data;
    import lombok.NoArgsConstructor;

    @Data
    @NoArgsConstructor
    public class WeatherResponse {
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
    }
