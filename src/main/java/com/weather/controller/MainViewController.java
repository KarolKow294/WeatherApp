package com.weather.controller;

import com.weather.model.*;
import com.weather.model.client.OpenWeatherClient;
import com.weather.model.service.WeatherService;
import com.weather.model.service.WeatherServiceFactory;
import com.weather.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController extends BaseController implements Initializable {
    public static final int FIRST_FORECAST_AFTER_ONE_DAY = 7;
    public static final int TOTAL_NUMBER_OF_FORECAST_DATA = 40;
    public static final int FORECAST_DATA_PER_DAY = 7;
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
        weatherService = WeatherServiceFactory.createService();
    }

    @FXML
    private void checkWeatherAction() throws Exception {
        Weather myWeather = checkWeatherForCity(cityField.getText(), countryField.getText());
        Weather destinationWeather = checkWeatherForCity(destinationCityField.getText(), destinationCountryField.getText());

        if (myWeather == null || destinationWeather == null){
            //errorLabel.setText("Cities are not in the database");
        } else {
            displayWeather(myWeather, "displayLeftColumn");
            displayWeather(destinationWeather, "displayRightColumn");
        }
    }

    private Weather checkWeatherForCity(String city, String country) throws IOException {
        Weather weather = weatherService.getWeather(city, country);
        return weather;
    }

    private void displayWeather(Weather weather, String column) throws Exception {
        switch (column) {
            case "displayLeftColumn":
                //myWeatherLabel.setText("aaa");
                //for(int i = FIRST_FORECAST_AFTER_ONE_DAY; i < TOTAL_NUMBER_OF_FORECAST_DATA; i++)
                //{
                    ForecastData forecastData = weather.getForecastData().get(1);

                    double temp = (Math.round((forecastData.temp - 272.15) * 100)) / 100.0;
                    //Date date = new Date(forecastData.dt*1000);
                    //Format format = new SimpleDateFormat("dd-MMMM-yyyy HH:mm:ss");
                    //String formattedDate = format.format(date);
                    //forecastDate.setText(formattedDate);
                    myWeatherLabel.setText("temperature: " + temp + " C");
                    myWeatherLabel.setText("pressure: "+ forecastData.pressure +" hPa");
                    //humidityForecast.setText("humidity: "+ forecastData.humidity +"%");

                    //i += FORECAST_DATA_PER_DAY;
                //}
            case "displayRightColumn":
                destinationWeatherLabel.setText("aaa");
                break;
            default:
                throw new Exception("Wrong column do display");
        }
    }
}
