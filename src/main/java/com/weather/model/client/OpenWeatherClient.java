package com.weather.model.client;

import com.google.gson.Gson;
import com.weather.model.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class OpenWeatherClient implements WeatherClient {
    public static final int TOTAL_NUMBER_OF_FORECAST_DATA = 40;
    Weather weather;
    public Weather getWeather(String city, String country) throws IOException {

        String webPageForCoordinates = "http://api.openweathermap.org/geo/1.0/direct?q="
                + city + "," + country + "&limit=1&appid=" + Config.API_KEY;
        URL coordinatesUrl = new URL(webPageForCoordinates);
        Coordinates coordinates = getCityCoordinates(coordinatesUrl);

        String webPageForWeather = "http://api.openweathermap.org/data/2.5/forecast?lat="
                + coordinates.getLat() + "&lon=" + coordinates.getLon() + "&appid=" + Config.API_KEY;
        URL weatherUrl = new URL(webPageForWeather);
        ArrayList<ForecastData> weatherData = getWeatherData(weatherUrl);

        return new Weather(weatherData);
    }

    private Coordinates getCityCoordinates(URL url) throws IOException {
        int response = getResponse(url);

        if (response == 200) {
            String jsonString = getJsonStringFromUrl(url);
            if (jsonString.isEmpty()) {
                return new Coordinates();
            } else {
                Gson gson = new Gson();
                return gson.fromJson(jsonString, Coordinates.class);
            }
        } else {
            return null;
        }
    }

    private int getResponse(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        return conn.getResponseCode();
    }

    private String getJsonStringFromUrl(URL url) throws IOException {
        String inline = "";
        Scanner scanner = new Scanner(url.openStream());

        while (scanner.hasNext()) {
            inline += scanner.nextLine();
        }
        scanner.close();

        String jsonString = inline.substring(1, inline.length() - 1);
        return jsonString;
    }

    private ArrayList<ForecastData> getWeatherData(URL url) throws IOException {
        int response = getResponse(url);

        if (response == 200) {
            String inline = getStringForJson(url);

            if (inline.isEmpty()) {
                return null;
            }
            else {
                ArrayList<ForecastData> forecastDataList= new ArrayList<ForecastData>();
                Gson gson = new Gson();
                ConvertData convertData = gson.fromJson(inline, ConvertData.class);

                for(int i = 0; i< TOTAL_NUMBER_OF_FORECAST_DATA; i++){
                    String listItem = gson.toJson(convertData.list.get(i));

                    ConvertData data = gson.fromJson(listItem, ConvertData.class);
                    String dt = gson.toJson(data.dt);
                    String weather = gson.toJson(data.weather);
                    String mainData = gson.toJson(data.main);

                    String finalString = "{\"dt\":" + dt + "," + mainData.substring(1, mainData.length() - 1)
                            + "," + weather.substring(2, weather.length() - 1);

                    ForecastData forecastData = gson.fromJson(finalString, ForecastData.class);

                    forecastDataList.add(forecastData);
                }
                return forecastDataList;
            }
        }
        else {
            return null;
        }
    }

    private String getStringForJson(URL url) throws IOException {
        String inline = "";
        Scanner scanner = new Scanner(url.openStream());

        while (scanner.hasNext()) {
            inline += scanner.nextLine();
        }
        scanner.close();

        return inline;
    }
}
