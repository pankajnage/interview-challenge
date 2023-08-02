package com.panks.weather.weatherapi.payload;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Wind {
    private  double speed;
    private int deg;
}
