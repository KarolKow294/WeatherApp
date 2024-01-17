package com.weather.model.service;

import com.weather.model.Weather;
import com.weather.model.client.OpenWeatherClient;
import com.weather.model.client.WeatherClient;

public class WeatherService {
    private final WeatherClient weatherClient;

    public WeatherService(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    public Weather getWeather(String city, String country) {
        return weatherClient.getWeather(city, country);
    }
}
