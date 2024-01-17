package com.weather.model.client;

import com.weather.model.Weather;

public interface WeatherClient {
    Weather getWeather(String city, String country);
}
