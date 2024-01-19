package com.project.app.service;

import com.project.app.constant.Path;
import com.project.app.dto.AverageTemperatureDto;
import com.project.app.dto.CityApiRequestDto;
import com.project.app.enumeration.ErrorCode;
import com.project.app.exception.DataNotFoundException;
import com.project.app.exception.InternalErrorException;
import com.project.app.mapper.CityMapper;
import com.project.app.model.CityModel;
import com.project.app.model.WeatherModel;
import com.project.app.repository.CityRepository;
import com.project.app.utils.WeatherUtilities;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.project.app.constant.WeatherURIConstant.*;
import static com.project.app.utils.WeatherUtilities.calculateAverage;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;

    private final CityRepository cityRepository;
    private final ValidationService validationService;

    private final CityMapper cityMapper;

    public WeatherService(RestTemplate restTemplate, CityRepository cityRepository, ValidationService validationService, CityMapper cityMapper) {
        this.restTemplate = restTemplate;
        this.cityRepository = cityRepository;
        this.validationService = validationService;
        this.cityMapper = cityMapper;
    }

    public WeatherModel readWeather(CityApiRequestDto cityApiRequestDto) {
        WeatherModel weatherModel;
        HttpEntity<Void> httpEntity = new HttpEntity<>(null);
        String uri = UriComponentsBuilder.fromUriString(Path.API_FORECAST)
                .queryParam(LATITUDE, cityApiRequestDto.getGeoCoordinate().getLat())
                .queryParam(LONGITUDE, cityApiRequestDto.getGeoCoordinate().getLng())
                .queryParam(HOURLY, "temperature_2m,weather_code")
                .queryParam(TIMEZONE, "auto")
                .build().toUriString();

        try {
            weatherModel = this.restTemplate.exchange(uri,
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<WeatherModel>() {
                    }).getBody();

        } catch (Exception e) {
            throw generateInternalError(e);
        }

        WeatherUtilities.weatherCodeConversion(weatherModel);
        return weatherModel;
    }

    public AverageTemperatureDto getAvgTemperatureByCityAndNumberOfDays(CityApiRequestDto cityApiRequestDto, int days){
        WeatherModel weatherModel;
        HttpEntity<Void> httpEntity = new HttpEntity<>(null);

        String uri = UriComponentsBuilder.fromUriString(Path.API_FORECAST)
                .queryParam(LATITUDE, cityApiRequestDto.getGeoCoordinate().getLat())
                .queryParam(LONGITUDE, cityApiRequestDto.getGeoCoordinate().getLng())
                .queryParam(HOURLY, "temperature_2m")
                .queryParam("forecast_days", days)
                .queryParam(TIMEZONE, "auto")
                .build().toUriString();

        try {
            weatherModel = this.restTemplate.exchange(uri,
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<WeatherModel>() {
                    }).getBody();

        } catch (Exception e) {
            throw generateInternalError(e);
        }

        List<Double> temperaturesInCelsius = weatherModel.getHourly().getTemperatureInCelsius();
        AverageTemperatureDto averageTemperatureDto = new AverageTemperatureDto();
        averageTemperatureDto.setAverageTemperature(calculateAverage(temperaturesInCelsius));
        return averageTemperatureDto;
    }

    public AverageTemperatureDto getAvgTemperatureByProvince(LocalDate date, String province){

        boolean provinceExists;
        try {
            provinceExists = cityRepository.existsByProvinciaIgnoreCase(province);
        } catch (Exception e) {
            throw generateInternalError(e);
        }

        if(!provinceExists){
            throw generateDataNotFound(province);
        }

        List<CityModel> cityModels;
        try {
            cityModels = cityRepository.findByProvincia(province).stream()
                    .map(cityMapper::fromEntityToModel)
                    .collect(Collectors.toList());
        } catch (Exception e){
            throw generateInternalError(e);
        }

        if (cityModels.isEmpty())
            throw generateDataNotFound(province);

        String latitudes = cityModels
                .stream().map(cityModel -> cityModel.getGeoCoordinate().getLat()).collect(Collectors.joining(","));

        String longitudes = cityModels
                .stream().map(cityModel -> cityModel.getGeoCoordinate().getLng()).collect(Collectors.joining(","));


        String uri = UriComponentsBuilder.fromUriString(Path.API_FORECAST)
                .queryParam(LATITUDE, latitudes)
                .queryParam(LONGITUDE,longitudes)
                .queryParam(HOURLY, "temperature_2m")
                .queryParam(TIMEZONE, "auto")
                .queryParam("start_date", date)
                .queryParam("end_date", date)
                .build().toUriString();

        HttpEntity<Void> httpEntity = new HttpEntity<>(null);
        List<WeatherModel> weatherModels;
        try {
            weatherModels = this.restTemplate.exchange(uri,
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<List<WeatherModel>>() {
                    }).getBody();

        } catch (Exception e) {
            throw generateInternalError(e);
        }

        List<Double> averageDailyTownTemperatures = new ArrayList<>();
        for(WeatherModel weatherModel : weatherModels){
            List<Double> hourlyTownTemperatureInCelsius = weatherModel.getHourly().getTemperatureInCelsius();
            double averageDailyTownTemperature = calculateAverage(hourlyTownTemperatureInCelsius);
            averageDailyTownTemperatures.add(averageDailyTownTemperature);
        }

        AverageTemperatureDto averageTemperatureDto = new AverageTemperatureDto();
        averageTemperatureDto.setAverageTemperature(calculateAverage(averageDailyTownTemperatures));
        return averageTemperatureDto;
    }


    private InternalErrorException generateInternalError(Exception e) {
        return new InternalErrorException("Something went wrong", e, ErrorCode.INTERNAL_ERROR);
    }

    private DataNotFoundException generateDataNotFound(String missingProperty) {
        return new DataNotFoundException("No province found for field : " + missingProperty, ErrorCode.DATA_NOT_FOUND);
    }


}

