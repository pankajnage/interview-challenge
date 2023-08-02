package com.panks.weather.weatherapi.services.impl;

import com.panks.weather.weatherapi.entities.WeatherData;
import com.panks.weather.weatherapi.payload.Weather;
import com.panks.weather.weatherapi.payload.WeatherApiResponse;
import com.panks.weather.weatherapi.repositories.WeatherDataRepository;
import com.panks.weather.weatherapi.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WeatherDataRepository weatherDataRepository;

    String urlLink="http://api.openweathermap.org/data/2.5/weather";
    @Override
    public String getWeatherData(String city, String country, String apiKey) {

        String url = String.format("%s?q=%s,%s&appid=%s", urlLink, city, country, apiKey);

        WeatherData response= restTemplate.getForObject(url, WeatherData.class);

        saveWeatherData(response);
        return response.getWeather().get(0).getDescription();
    }

    @Override
    public List<WeatherData> getAllCachedWeatherData() {
        return weatherDataRepository.findAll();
    }

    private void saveWeatherData(WeatherData weatherData) {
        weatherDataRepository.save(weatherData);

    }


}
