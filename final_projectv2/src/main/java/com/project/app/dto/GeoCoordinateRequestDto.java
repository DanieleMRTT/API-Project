package com.project.app.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GeoCoordinateRequestDto {

    @NotNull(message = "should not be null")
    private Integer istat;
    @NotBlank(message = "should not be blank")
    private String comune;
    @NotBlank(message = "should not be blank")
    private String lng;
    @NotBlank(message = "should not be blank")
    private String lat;
}
