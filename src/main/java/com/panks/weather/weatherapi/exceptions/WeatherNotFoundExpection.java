package com.panks.weather.weatherapi.exceptions;

public class WeatherNotFoundExpection extends RuntimeException{

    public WeatherNotFoundExpection(){
        super("Weather Data Not Found");
    }

    public WeatherNotFoundExpection(String msg){
        super(msg);
    }

}
