package com.project.app.mapper;

import com.project.app.dto.CityGeoCoordinateResponseDto;
import com.project.app.dto.GeoCoordinateRequestDto;
import com.project.app.dto.GeoCoordinateResponseDto;
import com.project.app.entity.GeoCoordinateEntity;
import com.project.app.model.GeoCoordinateModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GeoCoordinateMapper {

    GeoCoordinateModel fromEntityToModel(GeoCoordinateEntity geoCoordinateEntity);

    GeoCoordinateEntity fromModelToEntity(GeoCoordinateModel geoCoordinateModel);

    CityGeoCoordinateResponseDto fromModelToResponse(GeoCoordinateModel geoCoordinateModel);
    GeoCoordinateResponseDto responseFromModel(GeoCoordinateModel geoCoordinateModel);

    GeoCoordinateModel fromRequestToModel(GeoCoordinateRequestDto geoCoordinateRequestDto);
    
}
