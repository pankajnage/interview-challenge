package com.panks.weather.weatherapi.services;

import com.panks.weather.weatherapi.entities.WeatherData;
import com.panks.weather.weatherapi.payload.Weather;
import com.panks.weather.weatherapi.payload.WeatherApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WeatherService {



    // get Weather
    public String getWeatherData(String city, String country, String apiKey);

    // get All Weather Details from cache
    public List<WeatherData> getAllCachedWeatherData();


}
