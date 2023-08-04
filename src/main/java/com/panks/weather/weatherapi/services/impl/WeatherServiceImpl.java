package com.panks.weather.weatherapi.services.impl;

import com.panks.weather.weatherapi.entities.WeatherCachedData;
import com.panks.weather.weatherapi.entities.WeatherData;
import com.panks.weather.weatherapi.handlers.RestTemplateErrorHandler;
import com.panks.weather.weatherapi.repositories.WeatherCachedDataRepository;
import com.panks.weather.weatherapi.services.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Slf4j
public class WeatherServiceImpl implements WeatherService {

    private RestTemplate restTemplate;


    @Autowired
    public WeatherServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateErrorHandler())
                .build();
    }

    @Autowired
   private WeatherCachedDataRepository weatherCachedDataRepository;

    @Autowired
   private WeatherData response;

    @Autowired
    private WeatherCachedData weatherCachedDataBuilder;

    @Value("${openweathermap.api.url}")
    String urlLink;

   // String urlLink = "http://api.openweathermap.org/data/2.5/weather";

    @Override
    public String getWeatherData(String city, String country, String apiKey) {

        String cityCountry = city + country;
        Optional<WeatherCachedData> optionalWeatherCachedData = weatherCachedDataRepository.findById(cityCountry);

        if (optionalWeatherCachedData.isPresent()) {
            log.info("Fetched Data from H2 DB");
            response = optionalWeatherCachedData.get().getWeatherData();
        } else {
            log.info("Fetching Data from OpenWeatherApi");

            String url = String.format("%s?q=%s,%s&appid=%s", urlLink, city, country, apiKey);

            //Calling external Openweather API
            response = restTemplate.getForObject(url, WeatherData.class);

            // Saving data into Database
            saveWeatherCachedData(response, cityCountry);
        }

        return response.getWeather().get(0).getDescription();
    }


    private void saveWeatherCachedData(WeatherData weatherData, String cityCtry) {
        weatherCachedDataRepository.save(weatherCachedDataBuilder.builder().cityCountry(cityCtry).weatherData(weatherData).build());
    }


}
