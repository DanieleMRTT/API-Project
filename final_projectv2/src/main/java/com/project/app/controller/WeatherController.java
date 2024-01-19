package com.project.app.controller;

import com.project.app.constant.Path;
import com.project.app.mapper.WeatherMapper;
import com.project.app.service.ValidationService;
import com.project.app.service.WeatherService;
import com.project.app.dto.AverageTemperatureDto;
import com.project.app.dto.CityApiRequestDto;
import com.project.app.dto.ErrorResponseDto;
import com.project.app.dto.WeatherResponseDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
@OpenAPIDefinition
@RestController
@Tag(name = "Weather", description = "Endpoints for weather")
public class WeatherController {

    private final WeatherService weatherService;

    private final WeatherMapper weatherMapper;

    private final ValidationService validationService;

    public WeatherController(WeatherService weatherService, WeatherMapper weatherMapper, ValidationService validationService) {
        this.weatherService = weatherService;
        this.weatherMapper = weatherMapper;
        this.validationService = validationService;
    }
    @Operation(description = "Get the weather forecast of a City")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })

    @RequestMapping(value = Path.WEATHER_FORECAST + "/search/city", method = RequestMethod.POST)
    public ResponseEntity<WeatherResponseDto> getWeather(@Schema(description = "the Italian city", implementation = CityApiRequestDto.class)
                                                             @RequestBody CityApiRequestDto cityApiRequestDto) {
        validationService.doValidate(cityApiRequestDto);
        WeatherResponseDto weatherResponseDto = weatherMapper.fromModelToResponse(weatherService.readWeather(cityApiRequestDto));
        return ResponseEntity.ok(weatherResponseDto);

    }


    @Operation(description = "Get the average temperature of a City for a certain number of days")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })

    @Validated
    @RequestMapping(value = Path.DAILY_AVERAGE_TEMPERATURE_CITY + "/search/averageByCityAndNumberOfDays", method = RequestMethod.POST)
    public ResponseEntity<AverageTemperatureDto> getAvgTemperatureByCityAndNumberOfDays(@Schema(description = "the Italian city", implementation = CityApiRequestDto.class)
                                                                                            @RequestBody CityApiRequestDto cityApiRequestDto,
                                                                                        @Schema(description = "the number of days up to 16")
                                                                                        @RequestParam Integer days){

        validationService.doValidate(cityApiRequestDto);
        ValidationService.doValidateDays(days);
        return ResponseEntity.ok(weatherService.getAvgTemperatureByCityAndNumberOfDays(cityApiRequestDto, days));
    }
    @Operation(description = "Get the average temperature of all the towns for a certain province in a given date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @RequestMapping(value = Path.PROVINCE_AVERAGE_TEMPERATURE + "/search/averageByProvinceAndDay", method = RequestMethod.POST)
    public ResponseEntity<AverageTemperatureDto> getAvgTemperatureByProvince(@Schema(description = "the date up to 3 months from today, format: yyyy-mm-dd") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                                             @Schema(description = "the Italian province") @RequestParam String province){
        ValidationService.doValidateDate(date);
        return ResponseEntity.ok(weatherService.getAvgTemperatureByProvince(date,province));

    }
}
