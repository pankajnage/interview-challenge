package com.panks.weather.weatherapi.controllers;

import com.panks.weather.weatherapi.entities.WeatherData;
import com.panks.weather.weatherapi.exceptions.WeatherNotFoundExpection;
import com.panks.weather.weatherapi.payload.Weather;
import com.panks.weather.weatherapi.payload.WeatherApiResponse;
import com.panks.weather.weatherapi.services.WeatherService;
import com.panks.weather.weatherapi.utilities.RateLimiterUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    WeatherService weatherService;
    @Autowired
    RateLimiterUtility rateLimiterUtility;

    @PostMapping
    public ResponseEntity<String> getWeatherDescription(@RequestParam String city, @RequestParam String country, @RequestParam String apiKey){

        //Validate API Key
        if (!isValidApiKey(apiKey)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API Key");
        }

        // Check if rate limit is exceed
        if(rateLimiterUtility.rateLimitExceededValidator(apiKey)){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Hourly limit exceeded for API Key");
        }

        try{
            String description= weatherService.getWeatherData(city,country,apiKey);
            return ResponseEntity.ok(description);
        }
        catch(WeatherNotFoundExpection ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Weather Data Not Found");
        }


    }

    private boolean isRateLimitExceeded(String apiKey) {

        return rateLimiterUtility.rateLimitExceededValidator(apiKey);
    }

    private static final List<String> validApiKeys= Arrays.asList(
            "c9737ae97e4ce576407a520f30a486b1",
            "4d5ee2bc1a8c6791738b8f80a09151e7",
            "e2cecc8a1b344d02d443e86846f3fdc9",
            "2c50559d683dd7e9c3b172b0301e1ffa",
            "a778b7cc7f812536ef5960d93eccdf8a");
    private boolean isValidApiKey(String apiKey) {
        return validApiKeys.contains(apiKey);
    }

    @GetMapping
    public ResponseEntity<List<WeatherData>> getAllCachedWeather(){

        List<WeatherData> weatherData= weatherService.getAllCachedWeatherData();

        return  ResponseEntity.ok(weatherData);

    }

}
