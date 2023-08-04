package com.panks.weather.weatherapi.payload;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@Embeddable
@Entity
public class Weather {

    @Id
    @Column(insertable = false, updatable = false)
    private int id;
    private String main;
    private String description;
    private String icon;
}
