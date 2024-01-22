package com.weather.model.service;

import com.weather.model.client.OpenWeatherClient;
import com.weather.model.client.WeatherClient;

public class WeatherServiceFactory {
    public static WeatherService createService() {
        return new WeatherService((createWeatherClient()));
    }

    private static WeatherClient createWeatherClient(){
        return new OpenWeatherClient();
    }
}
