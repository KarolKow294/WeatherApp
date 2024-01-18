package com.weather.model;

import java.util.ArrayList;

public class Weather {
    private ArrayList<ForecastData> forecastData;

    public Weather(ArrayList<ForecastData> forecastData) {
        this.forecastData = forecastData;
    }

    public ArrayList<ForecastData> getForecastData() {
        return forecastData;
    }
}
