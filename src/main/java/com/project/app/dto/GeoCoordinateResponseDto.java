package com.project.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeoCoordinateResponseDto {

    private Integer istat;

    private String comune;

    private String lng;

    private String lat;
}
