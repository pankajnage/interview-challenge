package com.panks.weather.weatherapi.exceptions;

public class WeatherNotFoundExpection extends RuntimeException {

    public WeatherNotFoundExpection() {
        super("Weather data not found for provided city and country combination.");
    }

    public WeatherNotFoundExpection(String msg) {
        super(msg);
    }

}
