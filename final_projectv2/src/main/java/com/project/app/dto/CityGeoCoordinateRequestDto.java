package com.project.app.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CityGeoCoordinateRequestDto {

    @NotBlank(message = "should not be blank")
    private String lng;
    @NotBlank(message = "should not be blank")
    private String lat;
}
