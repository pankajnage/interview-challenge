package com.panks.weather.weatherapi.services.impl;

import com.panks.weather.weatherapi.entities.WeatherCachedData;
import com.panks.weather.weatherapi.entities.WeatherData;
import com.panks.weather.weatherapi.handlers.RestTemplateErrorHandler;
import com.panks.weather.weatherapi.payload.Weather;
import com.panks.weather.weatherapi.repositories.WeatherCachedDataRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherServiceImplTest {

    @Autowired
    private WeatherServiceImpl weatherServiceImpl;

   @MockBean
   private RestTemplateBuilder restTemplateBuilder;

   @MockBean
   private  RestTemplate restTemplate;

    @MockBean
    private WeatherCachedDataRepository weatherCachedDataRepository;

    @Autowired
    WeatherCachedData weatherCachedData;

    @MockBean
    RestTemplateErrorHandler restTemplateErrorHandler;

    @MockBean
    ResponseErrorHandler responseErrorHandler;



    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        when(restTemplateBuilder.errorHandler(any()).build()).thenReturn(restTemplate);
    }
    //@Test
  public void testGetWeatherDataFromCache(){
      String city="Melbourne";
      String country="au";
      String apiKey="Key-1";

      String cityCountry=city+""+country;

      WeatherData weatherData = new WeatherData();
      Weather weather = new Weather();
      weather.setDescription("Cloudy");
      weatherData.setWeather(List.of(weather));

      weatherCachedData= WeatherCachedData.builder().cityCountry(cityCountry).weatherData(weatherData).build();

      when(weatherCachedDataRepository.findById(cityCountry)).thenReturn(Optional.of(weatherCachedData));

      String description= weatherServiceImpl.getWeatherData(city,country,apiKey);

      Assertions.assertEquals(description,"Cloudy");

  }

 // @Test
    public void testGetWeatherDataFromOpenWeatherApi(){
        String city="Melbourne";
        String country="au";
        String apiKey="Key-1";

        String cityCountry=city+""+country;

        WeatherData weatherData = new WeatherData();
        Weather weather = new Weather();
        weather.setDescription("Partly Cloudy");
        weatherData.setWeather(List.of(weather));

        weatherCachedData= WeatherCachedData.builder().cityCountry(cityCountry).weatherData(weatherData).build();

        when(weatherCachedDataRepository.findById(cityCountry)).thenReturn(Optional.empty());
        when(restTemplate.getForObject(anyString(), eq(WeatherData.class))).thenReturn(weatherData);

        String description= weatherServiceImpl.getWeatherData(city,country,apiKey);

        Assertions.assertEquals(description,"Partly Cloudy");

    }

}
