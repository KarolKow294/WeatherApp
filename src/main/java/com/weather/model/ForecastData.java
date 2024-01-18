package com.weather.model;

public class ForecastData {
    public long dt;
    public String main;
    public String description;
    public String icon;
    public double temp;
    public double pressure;
    public int humidity;

    public ForecastData(long dt, String main, String description, double temp, double pressure, int humidity, String icon) {
        this.dt = dt;
        this.main = main;
        this.description = description;
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.icon = icon;
    }
}
