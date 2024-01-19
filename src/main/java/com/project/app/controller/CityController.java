package com.project.app.controller;

import com.project.app.constant.Path;
import com.project.app.mapper.CityMapper;
import com.project.app.model.CityModel;
import com.project.app.service.CityService;
import com.project.app.service.ValidationService;
import com.project.app.dto.CityCreateRequestDto;
import com.project.app.dto.CityResponseDto;
import com.project.app.dto.CityUpdateRequestDto;
import com.project.app.dto.ErrorResponseDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@OpenAPIDefinition
@RestController
@Tag(name = "Cities", description = "Endpoints for cities")
public class CityController {

    private final CityService cityService;

    private final CityMapper cityMapper;

    private final ValidationService validationService;


    public CityController(CityService cityService, CityMapper cityMapper, ValidationService validationService) {
        this.cityService = cityService;
        this.cityMapper = cityMapper;
        this.validationService = validationService;
    }

    @Operation(description = "Search all cities with a greater number of inhabitants than (Parameter)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR", content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    @RequestMapping(value = Path.CITY_WITH_INHABITANTS_GREATER_THAN + "/searchByInhabitants", method = RequestMethod.POST)
    public ResponseEntity<List<CityResponseDto>> getCitiesWithNumberOfInhabitantsGreaterThan(@Schema(description = "The number of inhabitants")
                                                                                                  @RequestParam Integer numberOfInhabitants){
        List<CityResponseDto> cityResponseDtos = cityService.getCitiesWithNumberOfInhabitantsGreaterThan(numberOfInhabitants).stream()
                .map(cityMapper::fromModelToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(cityResponseDtos);
    }
    @Operation(description = "Search all cities with a greater number of inhabitants than (Parameter) and in given Region(Parameter)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR", content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    @RequestMapping(value = Path.CITY_WITH_INHABITANTS_GREATER_THAN_IN_REGION + "/searchByInhabitantsAndRegion", method = RequestMethod.POST)
    public ResponseEntity<List<CityResponseDto>> getTownsInGivenRegionWithNumberOfInhabitantsGreaterThan(@Schema(description = "The number of inhabitants")
                                                                                                             @RequestParam Integer numberOfInhabitants,
                                                                                                         @Schema(description = "the Italian region") @RequestParam String region){
        List<CityResponseDto> cityResponseDtos = cityService.getTownsInGivenRegionWithNumberOfInhabitantsGreaterThan(region,numberOfInhabitants).stream()
                .map(cityMapper::fromModelToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(cityResponseDtos);

    }
    @Operation(description = "Search all cities with a greater number of inhabitants than (Parameter) and in given Province(Parameter)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR", content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    @RequestMapping(value = Path.CITY_WITH_INHABITANTS_GREATER_THAN_IN_PROVINCE + "/searchByInhabitantsAndProvince", method = RequestMethod.POST)
    public ResponseEntity<List<CityResponseDto>> getTownsInGivenProvinceWithNumberOfInhabitantsGreaterThan(@Schema(description = "The number of inhabitants")
                                                                                                         @RequestParam Integer numberOfInhabitants,
                                                                                                         @Schema(description = "The province") @RequestParam String province){
        List<CityResponseDto> cityResponseDtos = cityService.getTownsInGivenProvinceWithNumberOfInhabitantsGreaterThan(province,numberOfInhabitants).stream()
                .map(cityMapper::fromModelToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(cityResponseDtos);

    }


    @Operation(description = "Find a specific city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @RequestMapping(value = Path.CITY + "/{istat}", method = RequestMethod.GET)
    public ResponseEntity<CityResponseDto> readCity(@Schema(description = "The istat code of the Italian city")
                                                        @PathVariable("istat") Integer istatCode){
        return ResponseEntity.ok(cityMapper.fromModelToResponse(cityService.readCity(istatCode)));
    }


    @Operation(description = "Create a city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            )),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR", content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    @RequestMapping(value = Path.CITY, method = RequestMethod.POST)
    public ResponseEntity<CityResponseDto> createCity(@Schema(description = "The create city request", implementation = CityCreateRequestDto.class)
                @RequestBody CityCreateRequestDto cityCreateRequestDto){
        validationService.doValidate(cityCreateRequestDto);
        CityModel cityModel = cityService.createCity(cityMapper.fromCreateRequestToModel(cityCreateRequestDto));

        URI uri = new DefaultUriBuilderFactory(Path.CITY).builder()
                .pathSegment(cityModel.getIstat().toString())
                .build();

        return ResponseEntity.created(uri).body(cityMapper.fromModelToResponse(cityModel));

    }

    @Operation(description = "Delete a city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "NO CONTENT"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @RequestMapping(value = Path.CITY + "/{istat}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCity(@Schema(description = "The istat code of the Italian city")
                                       @PathVariable("istat") Integer istatCode) {
        cityService.deleteById(istatCode);
        return ResponseEntity.noContent().build();
    }


    @Operation(description = "Update a city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @RequestMapping(value = Path.CITY + "/{istat}", method = RequestMethod.PUT)
    public ResponseEntity<CityResponseDto> updateCity(@Schema(description = "The update city request", implementation = CityUpdateRequestDto.class)
                                        @RequestBody CityUpdateRequestDto cityUpdateRequestDto,
                                        @Schema(description = "The istat code of the Italian city")
                                        @PathVariable("istat") Integer istatCode) {
        validationService.doValidate(cityUpdateRequestDto);
        CityModel cityModel = cityService.updateCity(cityMapper.fromUpdateRequestToModel(cityUpdateRequestDto), istatCode);
        return ResponseEntity.ok(cityMapper.fromModelToResponse(cityModel));
    }

}
