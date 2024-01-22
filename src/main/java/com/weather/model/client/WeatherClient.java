package com.weather.model.client;

import com.weather.model.Weather;

import java.io.IOException;
import java.net.MalformedURLException;

public interface WeatherClient {
    Weather getWeather(String city, String country) throws IOException;
}
