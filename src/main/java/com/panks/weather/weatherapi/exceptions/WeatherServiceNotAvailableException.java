package com.panks.weather.weatherapi.exceptions;

public class WeatherServiceNotAvailableException extends RuntimeException {
    public WeatherServiceNotAvailableException() {
        super("Weather service is currently unavailable.");
    }

    public WeatherServiceNotAvailableException(String msg) {
        super(msg);
    }
}
