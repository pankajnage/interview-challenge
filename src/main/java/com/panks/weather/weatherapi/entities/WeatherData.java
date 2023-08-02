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
@Entity
@Component
public class WeatherData {

    @Id
    private long id;

    @Embedded
    private Coord coord;


    @Embedded
    //@Transient
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "main_entity_id")
    private List<Weather> weather;


    private String base;

    @Embedded
    //@Transient
    private Main main;

    private int visibility;

    @Embedded
    //@Transient
    private Wind wind;

    @Embedded
    //@Transient
    private Clouds clouds;
    private long dt;
    @Embedded
    //@Transient
    private Sys sys;
    private int timezone;
    private String name;
    private int cod;

}
