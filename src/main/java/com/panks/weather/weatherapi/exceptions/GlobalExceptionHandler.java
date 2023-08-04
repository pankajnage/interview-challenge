package com.panks.weather.weatherapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(WeatherNotFoundExpection.class)
    public ResponseEntity<String> handlerWeatherNotFoundException(WeatherNotFoundExpection ex){

        String responseMsg= ex.getMessage();

        return new ResponseEntity<String>(responseMsg, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WeatherServiceNotAvailableException.class)
    public ResponseEntity<String> handlerWeatherServiceNotAvailableException(WeatherServiceNotAvailableException ex){

        String responseMsg= ex.getMessage();

        return new ResponseEntity<String>(responseMsg, HttpStatus.NOT_FOUND);
    }


}
