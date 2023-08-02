package com.panks.weather.weatherapi.payload;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Clouds {
    @Column(name = "all_data")
    private int all;
}
