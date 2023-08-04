package com.panks.weather.weatherapi.handlers;

import com.panks.weather.weatherapi.exceptions.WeatherNotFoundExpection;
import com.panks.weather.weatherapi.exceptions.WeatherServiceNotAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
public class RestTemplateErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

        if(response.getStatusCode().is5xxServerError()){
            if(response.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE){
                throw new WeatherServiceNotAvailableException();
            }
        }
        else if(response.getStatusCode().is4xxClientError()){
            if(response.getStatusCode() == HttpStatus.NOT_FOUND){
                throw new WeatherNotFoundExpection();
            }
        }

    }
}
