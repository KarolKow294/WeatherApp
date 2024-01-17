package com.weather.model.client;

import com.weather.model.Weather;

public class OpenWeatherClient implements WeatherClient {
    Weather weather = new Weather();
    public Weather getWeather(String city, String countery) {
        System.out.println("print");
        return weather;
    }
}
