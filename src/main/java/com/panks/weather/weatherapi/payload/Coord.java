package com.panks.weather.weatherapi.payload;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Coord {
    private double lon;
    private double lat;
}
