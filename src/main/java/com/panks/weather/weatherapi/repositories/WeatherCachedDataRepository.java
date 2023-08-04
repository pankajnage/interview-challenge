package com.panks.weather.weatherapi.repositories;

import com.panks.weather.weatherapi.entities.WeatherCachedData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherCachedDataRepository extends JpaRepository<WeatherCachedData, String> {
}
