package com.panks.weather.weatherapi.entities;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="apiKey_rate")
@Builder
public class ApiKeyRate {
    @Id
    private String apiKey;

    @Column(nullable = false)
    private int requestCount;

    @Column(nullable = false)
    private LocalDateTime lastRequestTime;

}
