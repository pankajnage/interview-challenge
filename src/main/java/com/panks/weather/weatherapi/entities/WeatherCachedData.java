package com.panks.weather.weatherapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cached-weather-data")
@Builder
@Component
public class WeatherCachedData {

    @Id
    @Column(name = "city-country")
    private String cityCountry;

    @Column(name = "weather-data")
    @Embedded
    private WeatherData weatherData;

}
