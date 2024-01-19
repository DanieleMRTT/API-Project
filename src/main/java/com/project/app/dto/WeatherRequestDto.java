package com.project.app.dto;

import com.project.app.model.HourlyModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class WeatherRequestDto {

    @NotBlank(message = "should not be blank")
    private String latitude;
    @NotBlank(message = "should not be blank")
    private String longitude;
    @NotBlank(message = "should not be blank")
    private String timezone;
    @NotNull(message = "should not be null")
    private HourlyModel hourly;
}
