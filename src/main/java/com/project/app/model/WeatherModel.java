package com.project.app.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherModel {


    private String latitude;
    private String longitude;
    private String timezone;
    private HourlyModel hourly;


}
