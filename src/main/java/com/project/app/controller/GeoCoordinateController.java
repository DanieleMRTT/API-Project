package com.project.app.controller;

import com.project.app.constant.Path;
import com.project.app.dto.ErrorResponseDto;
import com.project.app.dto.GeoCoordinateRequestDto;
import com.project.app.dto.GeoCoordinateResponseDto;
import com.project.app.mapper.GeoCoordinateMapper;
import com.project.app.model.GeoCoordinateModel;
import com.project.app.service.CityService;
import com.project.app.service.GeoCoordinateService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@OpenAPIDefinition
@Tag(name = "GeoCoordinate", description = "Endpoints for geoCoordinates")
@RequestMapping(Path.GEO_COORDINATE)
public class GeoCoordinateController {

    
    private final GeoCoordinateService geoCoordinateService;

    private final GeoCoordinateMapper geoCoordinateMapper;
    
    private final CityService cityService;


    public GeoCoordinateController( GeoCoordinateService geoCoordinateService, GeoCoordinateMapper geoCoordinateMapper, CityService cityService) {
        this.geoCoordinateService = geoCoordinateService;
        this.geoCoordinateMapper = geoCoordinateMapper;
        this.cityService = cityService;
    }

    @Operation(description = "Read city's geoCoordinates")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("/{istat}/geoCoordinate")
    public ResponseEntity<GeoCoordinateResponseDto> readGeoCoordinate(@PathVariable("istat")
                                                             @Schema(description = "The unique identifier of a city")
                                                             int istat) {
        cityService.readCity(istat);
        return ResponseEntity.ok(geoCoordinateMapper.responseFromModel(geoCoordinateService.readGeoCoordinate(istat)));
    }

    @Operation(description = "Update city's geoCoordinates")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PutMapping("/{istat}/geoCoordinate")
    public ResponseEntity<GeoCoordinateResponseDto> updateGeoCoordinate(@Schema(description = "The update geoCoordinate request", implementation = GeoCoordinateRequestDto.class)
                                                                            @RequestBody GeoCoordinateRequestDto geoCoordinateRequestDto,
                                                                            @Schema(description = "The unique identifier of a city")
                                                                            @PathVariable("istat") int istat) {
        geoCoordinateService.deleteById(istat);
        GeoCoordinateModel geoCoordinateModel = geoCoordinateService.createGeoCoordinate(geoCoordinateMapper.fromRequestToModel(geoCoordinateRequestDto));
        return ResponseEntity.ok(geoCoordinateMapper.responseFromModel(geoCoordinateModel));
    }



    
}
