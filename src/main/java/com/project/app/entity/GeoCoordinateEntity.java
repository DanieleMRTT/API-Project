package com.project.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity(name = "italy_geo")
public class GeoCoordinateEntity {

    @Id
    @Column(name = "istat", unique = true)
    private Integer istat;
    @Column(name = "comune")
    private String comune;
    @Column(name = "lng")
    private String lng;
    @Column(name = "lat")
    private String lat;
    @OneToOne
    @JoinColumn(name = "istat")
    private CityEntity city;



}
