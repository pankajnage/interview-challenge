package com.panks.weather.weatherapi.services;

import org.springframework.stereotype.Service;



@Service
public interface WeatherService {



    // get Weather
    public String getWeatherData(String city, String country, String apiKey);



}
