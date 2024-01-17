package com.weather.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Weather {
    private double minTemperature;
    private double maxTemperature;
    private String description;
    private Date date;

    public Weather() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        //this.date = sdf.format(date);
    }
}
