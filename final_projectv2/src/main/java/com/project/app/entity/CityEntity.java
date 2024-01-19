package com.project.app.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

@Getter
@Setter
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "comune", "regione", "provincia" }) }, indexes = @Index(columnList = "num_residenti"))
@Entity(name = "italy_cities")
public class CityEntity {

    @Id
    @Column(name = "istat", unique = true)
    private Integer istat;
    @Column(name = "comune")
    private String comune;
    @Column(name = "regione")
    private String regione;
    @Column(name ="provincia")
    private String provincia;
    @Column(name = "prefisso")
    private String prefisso;
    @Column(name = "cod_fisco", unique = true)
    @JsonProperty("cod_fisco")
    private String codFisco;
    @Column(name = "superficie")
    private Double superficie;
    @Column(name = "num_residenti")
    @JsonProperty("num_residenti")
    private Integer numResidenti;
    @OneToOne(mappedBy = "city", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private GeoCoordinateEntity geoCoordinate;


}
