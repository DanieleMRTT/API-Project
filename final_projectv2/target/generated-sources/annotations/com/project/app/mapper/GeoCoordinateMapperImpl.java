package com.project.app.mapper;

import com.project.app.dto.CityGeoCoordinateResponseDto;
import com.project.app.dto.GeoCoordinateRequestDto;
import com.project.app.dto.GeoCoordinateResponseDto;
import com.project.app.entity.GeoCoordinateEntity;
import com.project.app.model.GeoCoordinateModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-19T12:56:04+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.4.1 (Oracle Corporation)"
)
@Component
public class GeoCoordinateMapperImpl implements GeoCoordinateMapper {

    @Override
    public GeoCoordinateModel fromEntityToModel(GeoCoordinateEntity geoCoordinateEntity) {
        if ( geoCoordinateEntity == null ) {
            return null;
        }

        GeoCoordinateModel geoCoordinateModel = new GeoCoordinateModel();

        geoCoordinateModel.setIstat( geoCoordinateEntity.getIstat() );
        geoCoordinateModel.setComune( geoCoordinateEntity.getComune() );
        geoCoordinateModel.setLng( geoCoordinateEntity.getLng() );
        geoCoordinateModel.setLat( geoCoordinateEntity.getLat() );

        return geoCoordinateModel;
    }

    @Override
    public GeoCoordinateEntity fromModelToEntity(GeoCoordinateModel geoCoordinateModel) {
        if ( geoCoordinateModel == null ) {
            return null;
        }

        GeoCoordinateEntity geoCoordinateEntity = new GeoCoordinateEntity();

        geoCoordinateEntity.setIstat( geoCoordinateModel.getIstat() );
        geoCoordinateEntity.setComune( geoCoordinateModel.getComune() );
        geoCoordinateEntity.setLng( geoCoordinateModel.getLng() );
        geoCoordinateEntity.setLat( geoCoordinateModel.getLat() );

        return geoCoordinateEntity;
    }

    @Override
    public CityGeoCoordinateResponseDto fromModelToResponse(GeoCoordinateModel geoCoordinateModel) {
        if ( geoCoordinateModel == null ) {
            return null;
        }

        CityGeoCoordinateResponseDto cityGeoCoordinateResponseDto = new CityGeoCoordinateResponseDto();

        cityGeoCoordinateResponseDto.setLng( geoCoordinateModel.getLng() );
        cityGeoCoordinateResponseDto.setLat( geoCoordinateModel.getLat() );

        return cityGeoCoordinateResponseDto;
    }

    @Override
    public GeoCoordinateResponseDto responseFromModel(GeoCoordinateModel geoCoordinateModel) {
        if ( geoCoordinateModel == null ) {
            return null;
        }

        GeoCoordinateResponseDto geoCoordinateResponseDto = new GeoCoordinateResponseDto();

        geoCoordinateResponseDto.setIstat( geoCoordinateModel.getIstat() );
        geoCoordinateResponseDto.setComune( geoCoordinateModel.getComune() );
        geoCoordinateResponseDto.setLng( geoCoordinateModel.getLng() );
        geoCoordinateResponseDto.setLat( geoCoordinateModel.getLat() );

        return geoCoordinateResponseDto;
    }

    @Override
    public GeoCoordinateModel fromRequestToModel(GeoCoordinateRequestDto geoCoordinateRequestDto) {
        if ( geoCoordinateRequestDto == null ) {
            return null;
        }

        GeoCoordinateModel geoCoordinateModel = new GeoCoordinateModel();

        geoCoordinateModel.setIstat( geoCoordinateRequestDto.getIstat() );
        geoCoordinateModel.setComune( geoCoordinateRequestDto.getComune() );
        geoCoordinateModel.setLng( geoCoordinateRequestDto.getLng() );
        geoCoordinateModel.setLat( geoCoordinateRequestDto.getLat() );

        return geoCoordinateModel;
    }
}
