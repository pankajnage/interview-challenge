package com.panks.weather.weatherapi.services.impl;

import com.panks.weather.weatherapi.entities.WeatherCachedData;
import com.panks.weather.weatherapi.entities.WeatherData;
import com.panks.weather.weatherapi.handlers.RestTemplateErrorHandler;
import com.panks.weather.weatherapi.payload.Weather;
import com.panks.weather.weatherapi.payload.WeatherApiResponse;
import com.panks.weather.weatherapi.repositories.WeatherCachedDataRepository;
//import com.panks.weather.weatherapi.repositories.WeatherDataRepository;
import com.panks.weather.weatherapi.services.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class WeatherServiceImpl implements WeatherService {

    private RestTemplate restTemplate;


    @Autowired
    public WeatherServiceImpl(RestTemplateBuilder restTemplateBuilder, WeatherCachedDataRepository weatherCachedDataRepository){

        restTemplate= restTemplateBuilder
                                   .errorHandler(new RestTemplateErrorHandler())
                                    .build();
        this.weatherCachedDataRepository = weatherCachedDataRepository;

    }

    WeatherCachedDataRepository weatherCachedDataRepository;

    @Autowired
    WeatherData response;

    @Autowired
    WeatherCachedData weatherCachedDataBuilder;

    String urlLink="http://api.openweathermap.org/data/2.5/weather";
    @Override
    public String getWeatherData(String city, String country, String apiKey) {

        String cityCountry= city+""+country;
        Optional<WeatherCachedData> optionalWeatherCachedData= weatherCachedDataRepository.findById(cityCountry);

        if(optionalWeatherCachedData.isPresent()){
            log.info("Fetched Data from H2 DB");
            response = optionalWeatherCachedData.get().getWeatherData();
        }

        else {
            log.info("Fetching Data from OpenWeatherApi");

            String url = String.format("%s?q=%s,%s&appid=%s", urlLink, city, country, apiKey);

             response = restTemplate.getForObject(url, WeatherData.class);



             saveWeatherCachedData(response, cityCountry);
        }

       // saveWeatherData(response);


        return response.getWeather().get(0).getDescription();
    }

    @Override
    public List<WeatherData> getAllCachedWeatherData() {
     //   return weatherDataRepository.findAll();
        return null;
    }

    private void saveWeatherData(WeatherData weatherData) {
       // weatherDataRepository.save(weatherData);
    }

    private void saveWeatherCachedData(WeatherData weatherData,String cityCtry) {
        weatherCachedDataRepository.save(weatherCachedDataBuilder.builder().cityCountry(cityCtry).weatherData(weatherData).build());
    }


}
