package com.project.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
@Setter
public class GeoCoordinateModel {


    private Integer istat;

    private String comune;

    private String lng;

    private String lat;

}
