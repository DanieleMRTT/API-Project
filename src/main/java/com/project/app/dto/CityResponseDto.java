package com.project.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityResponseDto {


    private Integer istat;

    private String comune;

    private String regione;

    private String provincia;

    private String prefisso;

    @JsonProperty("cod_fisco")
    private String codFisco;

    private Double superficie;
    @JsonProperty("num_residenti")
    private Integer numResidenti;

    private CityGeoCoordinateResponseDto geoCoordinate;
}
