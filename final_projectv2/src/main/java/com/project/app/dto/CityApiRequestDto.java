package com.project.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CityApiRequestDto {

    @NotNull(message = "Should not be null")
    @Schema(description = "The name of the town")
    private String comune;
    @NotNull(message = "Should not be null")
    @NotBlank(message = "Should not be blank")
    @Schema(description = "The name of the region")
    private String regione;
    @NotNull(message = "Should not be null")
    @NotBlank(message = "Should not be blank")
    @Schema(description = "The name of the provincia")
    @Length(min = 2, max = 2, message = "the length must be 2 characters")
    private String provincia;
    @NotNull(message = "Should not be null")
    @NotBlank(message = "Should not be blank")
    @Schema(description = "The prefix")
    @Length(max = 7, message = "the max length is 7 characters")
    private String prefisso;
    @NotBlank(message = "Should not be blank")
    @Schema(description = "The tax code")
    @Length(min = 1, max = 6, message = "must be between 1 to 5 characters long")
    @JsonProperty("cod_fisco")
    private String codFisco;
    @NotNull(message = "Should not be null")
    @Schema(description = "The surface area in sq km")
    private Double superficie;
    @NotNull(message = "Should not be null")
    @Schema(description = "The number of inhabitants")
    @JsonProperty("num_residenti")
    private Integer numResidenti;
    @NotNull
    private CityGeoCoordinateRequestDto geoCoordinate;
}
