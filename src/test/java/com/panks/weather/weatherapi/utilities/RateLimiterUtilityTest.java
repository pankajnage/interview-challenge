package com.panks.weather.weatherapi.utilities;

import com.panks.weather.weatherapi.entities.ApiKeyRate;
import com.panks.weather.weatherapi.repositories.ApiKeyRateRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RateLimiterUtilityTest {
    @InjectMocks
    RateLimiterUtility rateLimiterUtility;

    @MockBean
    ApiKeyRateRepository apiKeyRateRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testApiKeyNotPresentInDatabase() {
        String apiKey = "Key-1";
        ApiKeyRate apiKeyRate = ApiKeyRate.builder().build();

        when(apiKeyRateRepository.findById(apiKey)).thenReturn(Optional.empty());

        Boolean result = rateLimiterUtility.rateLimitExceededValidator(apiKey);

        Assertions.assertFalse(result);

    }


    @Test
    public void testApiKeyPresentInDBAndDifferenceMoreThanHr() {
        String apiKey = "Key-1";
        ApiKeyRate apiKeyRate = ApiKeyRate.builder().apiKey(apiKey).requestCount(1).lastRequestTime(LocalDateTime.now().minusMinutes(120)).build();
        Optional<ApiKeyRate> apiKeyDetails = Optional.of(apiKeyRate);

        when(apiKeyRateRepository.findById(apiKey)).thenReturn(apiKeyDetails);

        Boolean result = rateLimiterUtility.rateLimitExceededValidator(apiKey);

        Assertions.assertFalse(result);

    }

    @Test
    public void testDifferenceLessThanHrAndReqCountLessThan5() {
        String apiKey = "Key-1";
        ApiKeyRate apiKeyRate = ApiKeyRate.builder().apiKey(apiKey).requestCount(1).lastRequestTime(LocalDateTime.now().minusMinutes(40)).build();
        Optional<ApiKeyRate> apiKeyDetails = Optional.of(apiKeyRate);

        when(apiKeyRateRepository.findById(apiKey)).thenReturn(apiKeyDetails);

        Boolean result = rateLimiterUtility.rateLimitExceededValidator(apiKey);
        Assertions.assertFalse(result);

    }

    @Test
    public void testDifferenceLessThanHrAndReqCount5() {
        String apiKey = "Key-1";
        ApiKeyRate apiKeyRate = ApiKeyRate.builder().apiKey(apiKey).requestCount(5).lastRequestTime(LocalDateTime.now().minusMinutes(40)).build();
        Optional<ApiKeyRate> apiKeyDetails = Optional.of(apiKeyRate);

        when(apiKeyRateRepository.findById(apiKey)).thenReturn(apiKeyDetails);

        Boolean result = rateLimiterUtility.rateLimitExceededValidator(apiKey);
        Assertions.assertTrue(result);
    }


}
