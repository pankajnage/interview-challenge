package com.panks.weather.weatherapi.utilities;

import com.panks.weather.weatherapi.entities.ApiKeyRate;
import com.panks.weather.weatherapi.repositories.ApiKeyRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class RateLimiterUtility {

    @Autowired
    ApiKeyRateRepository apiKeyRateRepository;

    private static final int RATE_LIMIT=5;
    private static final long TOTAL_TIME_IN_MIN=60;



public Boolean rateLimitExceededValidator(String apiKey){

    Optional<ApiKeyRate> apiKeyDetails= apiKeyRateRepository.findById(apiKey);
    if(! apiKeyDetails.isPresent()){

        ApiKeyRate apiKeyRate = ApiKeyRate.builder().apiKey(apiKey).requestCount(1).lastRequestTime(LocalDateTime.now()).build();
        apiKeyRateRepository.save(apiKeyRate);
        return false;
    }
    else if(apiKeyDetails.isPresent()){
        Duration duration= Duration.between(apiKeyDetails.get().getLastRequestTime(),LocalDateTime.now());
        long Time_Differnce_InMin= duration.toMinutes();

        // time difference is more than hour
        if(Time_Differnce_InMin > TOTAL_TIME_IN_MIN){
            ApiKeyRate apiKeyRate = ApiKeyRate.builder().apiKey(apiKey).requestCount(1).lastRequestTime(LocalDateTime.now()).build();
            apiKeyRateRepository.save(apiKeyRate);
            return false;
        }

        // time difference is less than an hour and request count is less than 5
        if((Time_Differnce_InMin < TOTAL_TIME_IN_MIN) && (apiKeyDetails.get().getRequestCount() < RATE_LIMIT)){
            int counter= apiKeyDetails.get().getRequestCount();
            ApiKeyRate apiKeyRate = ApiKeyRate.builder().apiKey(apiKey).requestCount(++counter).lastRequestTime(LocalDateTime.now()).build();
            apiKeyRateRepository.save(apiKeyRate);
            return false;
        }
        return true;
    }

    else{
        return true;
    }

}
}
