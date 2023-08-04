package com.panks.weather.weatherapi.payload;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Sys {
    private int type;
    @Column(insertable = false, updatable = false)
    private int id;
    private String country;
    private long sunrise;
    private long sunset;
}
