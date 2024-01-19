package com.project.app.utils;

import com.project.app.constant.WeatherConstant;
import com.project.app.model.WeatherModel;

import java.util.List;
import java.util.stream.Collectors;

public class WeatherUtilities {

    public static void weatherCodeConversion(WeatherModel weatherModel){

        if(weatherModel != null) {
            List<String> weatherCodesConverted = weatherModel.getHourly().getWeatherCode().stream()
                    .map(WeatherConstant::convertWeatherCode)
                    .collect(Collectors.toList());
            weatherModel.getHourly().setWeatherCode(weatherCodesConverted);

        }
    }

    public static double calculateAverage(List<Double> temperaturesInCelsius){
        return temperaturesInCelsius.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .getAsDouble();
    }
}
