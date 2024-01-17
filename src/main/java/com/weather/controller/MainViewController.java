package com.weather.controller;

import com.weather.model.*;
import com.weather.model.service.WeatherService;
import com.weather.model.service.WeatherServiceFactory;
import com.weather.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController extends BaseController implements Initializable {
    private WeatherService weatherService;

    @FXML
    private TextField cityField;

    @FXML
    private TextField countryField;

    @FXML
    private TextField destinationCityField;

    @FXML
    private TextField destinationCountryField;

    @FXML
    private Label myWeatherLabel;

    @FXML
    private Label destinationWeatherLabel;

    public MainViewController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //coordinatesService = CoordinatesServiceFactory.createService();
        weatherService = WeatherServiceFactory.createService();
        //setVisible temperature
    }

    @FXML
    private void checkWeatherAction() throws Exception {
        Weather myWeather = checkWeatherForCity(cityField.getText(), countryField.getText());
        Weather destinationWeather = checkWeatherForCity(destinationCityField.getText(), destinationCountryField.getText());

        displayWeather(myWeather, "displayLeftColumn");
        displayWeather(destinationWeather, "displayRightColumn");
    }

    private Weather checkWeatherForCity(String city, String country) {
        Weather weather = weatherService.getWeather(city, country);
        return weather;
    }

    private void displayWeather(Weather weather, String column) throws Exception {
        switch (column) {
            case "displayLeftColumn":
                myWeatherLabel.setText("aaa");
                break;
            case "displayRightColumn":
                destinationWeatherLabel.setText("aaa");
                break;
            default:
                throw new Exception("Wrong column do display");
        }
    }
}
