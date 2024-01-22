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
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class MainViewController extends BaseController implements Initializable {
    public static final int FIRST_FORECAST = 5;
    public static final int TOTAL_NUMBER_OF_FORECAST_DATA = 40;
    public static final int FORECAST_INCREMENT = 7;
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
    private Label myWeatherLabel1;
    @FXML
    private Label myWeatherLabel2;
    @FXML
    private Label myWeatherLabel3;
    @FXML
    private Label myWeatherLabel4;
    @FXML
    private Label myWeatherLabel5;
    @FXML
    private Label destinationWeatherLabel1;
    @FXML
    private Label destinationWeatherLabel2;
    @FXML
    private Label destinationWeatherLabel3;
    @FXML
    private Label destinationWeatherLabel4;
    @FXML
    private Label destinationWeatherLabel5;

    @FXML
    private Label errorLabel;

    public MainViewController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        weatherService = WeatherServiceFactory.createService();
    }

    @FXML
    private void checkWeatherAction() throws Exception {
        errorLabel.setText("");
        try {
            Weather myWeather = checkWeatherForCity(cityField.getText(), countryField.getText());
            Weather destinationWeather = checkWeatherForCity(destinationCityField.getText(), destinationCountryField.getText());
            displayWeather(myWeather, "displayLeftColumn");
            displayWeather(destinationWeather, "displayRightColumn");
        } catch (Exception e) {
            errorLabel.setText("Fields are empty or cities don't exist.");
        }
    }

    private Weather checkWeatherForCity(String city, String country) throws IOException {
        Weather weather = weatherService.getWeather(city, country);
        return weather;
    }

    private void displayWeather(Weather weather, String column) throws Exception {
        int labelCounter = 1;

        for(int i = FIRST_FORECAST; i < TOTAL_NUMBER_OF_FORECAST_DATA ; i++) {
            ForecastData forecastData = weather.getForecastData().get(i);
            switch (column) {
                case "displayLeftColumn":
                    setDataToLabel(forecastData, labelCounter);
                    break;
                case "displayRightColumn":
                    setDataToLabel(forecastData, labelCounter + 5);
                    break;
                default:
                    throw new Exception("Wrong column to display");
            }
            labelCounter += 1;
            i += FORECAST_INCREMENT;
        }
    }

    private void setDataToLabel(ForecastData forecastData, int labelCounter) throws Exception {
        double temp = (Math.round((forecastData.temp - 272.15) * 100)) / 100.0;
        Date date = new Date(forecastData.dt*1000);
        Format format = new SimpleDateFormat("dd-MM-yyyy HH:ss");
        String formattedDate = format.format(date);

        String dataToSet = "Date: " + formattedDate + "\n"
                + "Description: " + forecastData.main + "\n"
                + "Temperature: " + temp + " C\n"
                + "Pressure: " + forecastData.pressure +" hPa\n"
                + "Humidity: " + forecastData.humidity +"%";

        switch (labelCounter) {
            case 1: myWeatherLabel1.setText(dataToSet); break;
            case 2: myWeatherLabel2.setText(dataToSet); break;
            case 3: myWeatherLabel3.setText(dataToSet); break;
            case 4: myWeatherLabel4.setText(dataToSet); break;
            case 5: myWeatherLabel5.setText(dataToSet); break;
            case 6: destinationWeatherLabel1.setText(dataToSet); break;
            case 7: destinationWeatherLabel2.setText(dataToSet); break;
            case 8: destinationWeatherLabel3.setText(dataToSet); break;
            case 9: destinationWeatherLabel4.setText(dataToSet); break;
            case 10: destinationWeatherLabel5.setText(dataToSet); break;
            default: throw new Exception("Wrong label to display");
        }
    }
}
