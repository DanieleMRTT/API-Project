package com.project.app.dto;

import com.project.app.model.HourlyModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class WeatherResponseDto {

    @NotBlank
    private String latitude;
    @NotBlank
    private String longitude;
    @NotNull
    private HourlyModel hourly;
    @NotBlank
    private String timezone;
}
