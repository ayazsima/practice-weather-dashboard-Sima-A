package com.example.weather_dashboard.model;

public class WeatherResponse {
    private WeatherMeasurements main;
    private Weather[] weather;

    public WeatherMeasurements getMain() {
        return main;
    }

    public void setMain(WeatherMeasurements main) {
        this.main = main;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }
}
