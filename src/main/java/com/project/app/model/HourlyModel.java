package com.project.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class HourlyModel {

    @JsonProperty("time")
    private List<LocalDateTime> localDateTime;

    @JsonProperty("temperature_2m")
    private List<Double> temperatureInCelsius;

    @JsonProperty("weather_code")
    private List<String> weatherCode;


}
