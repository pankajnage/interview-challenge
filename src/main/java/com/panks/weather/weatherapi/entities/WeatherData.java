package com.panks.weather.weatherapi.entities;

import com.panks.weather.weatherapi.payload.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Embeddable
public class WeatherData {
    private long id;

    @Embedded
    private Coord coord;


    @Embedded
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "main_entity_id")
    private List<Weather> weather;


    private String base;

    @Embedded
    private Main main;

    private int visibility;

    @Embedded
    private Wind wind;

    @Embedded
    private Clouds clouds;
    private long dt;
    @Embedded
    private Sys sys;
    private int timezone;
    private String name;
    private int cod;

}
