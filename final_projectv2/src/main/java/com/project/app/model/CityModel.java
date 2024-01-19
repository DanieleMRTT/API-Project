package com.project.app.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityModel {

    private Integer istat;

    private String comune;

    private String regione;

    private String provincia;

    private String prefisso;

    private String codFisco;

    private Double superficie;

    private Integer numResidenti;

    private GeoCoordinateModel geoCoordinate;
}
