module WeatherApp {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.web;
    requires java.desktop;
    requires com.google.gson;

    opens com.weather;
    opens com.weather.view;
    opens com.weather.controller;
    opens com.weather.model;
    opens com.weather.model.service;
}