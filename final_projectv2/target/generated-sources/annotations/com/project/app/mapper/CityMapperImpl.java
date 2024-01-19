package com.project.app.mapper;

import com.project.app.dto.CityCreateRequestDto;
import com.project.app.dto.CityGeoCoordinateRequestDto;
import com.project.app.dto.CityGeoCoordinateResponseDto;
import com.project.app.dto.CityResponseDto;
import com.project.app.dto.CityUpdateRequestDto;
import com.project.app.entity.CityEntity;
import com.project.app.entity.GeoCoordinateEntity;
import com.project.app.model.CityModel;
import com.project.app.model.GeoCoordinateModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-19T12:56:04+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.4.1 (Oracle Corporation)"
)
@Component
public class CityMapperImpl implements CityMapper {

    @Override
    public CityModel fromEntityToModel(CityEntity cityEntity) {
        if ( cityEntity == null ) {
            return null;
        }

        CityModel cityModel = new CityModel();

        cityModel.setIstat( cityEntity.getIstat() );
        cityModel.setComune( cityEntity.getComune() );
        cityModel.setRegione( cityEntity.getRegione() );
        cityModel.setProvincia( cityEntity.getProvincia() );
        cityModel.setPrefisso( cityEntity.getPrefisso() );
        cityModel.setCodFisco( cityEntity.getCodFisco() );
        cityModel.setSuperficie( cityEntity.getSuperficie() );
        cityModel.setNumResidenti( cityEntity.getNumResidenti() );
        cityModel.setGeoCoordinate( geoCoordinateEntityToGeoCoordinateModel( cityEntity.getGeoCoordinate() ) );

        return cityModel;
    }

    @Override
    public CityEntity fromModelToEntity(CityModel cityModel) {
        if ( cityModel == null ) {
            return null;
        }

        CityEntity cityEntity = new CityEntity();

        cityEntity.setIstat( cityModel.getIstat() );
        cityEntity.setComune( cityModel.getComune() );
        cityEntity.setRegione( cityModel.getRegione() );
        cityEntity.setProvincia( cityModel.getProvincia() );
        cityEntity.setPrefisso( cityModel.getPrefisso() );
        cityEntity.setCodFisco( cityModel.getCodFisco() );
        cityEntity.setSuperficie( cityModel.getSuperficie() );
        cityEntity.setNumResidenti( cityModel.getNumResidenti() );
        cityEntity.setGeoCoordinate( geoCoordinateModelToGeoCoordinateEntity( cityModel.getGeoCoordinate() ) );

        return cityEntity;
    }

    @Override
    public CityResponseDto fromModelToResponse(CityModel cityModel) {
        if ( cityModel == null ) {
            return null;
        }

        CityResponseDto cityResponseDto = new CityResponseDto();

        cityResponseDto.setIstat( cityModel.getIstat() );
        cityResponseDto.setComune( cityModel.getComune() );
        cityResponseDto.setRegione( cityModel.getRegione() );
        cityResponseDto.setProvincia( cityModel.getProvincia() );
        cityResponseDto.setPrefisso( cityModel.getPrefisso() );
        cityResponseDto.setCodFisco( cityModel.getCodFisco() );
        cityResponseDto.setSuperficie( cityModel.getSuperficie() );
        cityResponseDto.setNumResidenti( cityModel.getNumResidenti() );
        cityResponseDto.setGeoCoordinate( geoCoordinateModelToCityGeoCoordinateResponseDto( cityModel.getGeoCoordinate() ) );

        return cityResponseDto;
    }

    @Override
    public CityModel fromCreateRequestToModel(CityCreateRequestDto cityCreateRequestDto) {
        if ( cityCreateRequestDto == null ) {
            return null;
        }

        CityModel cityModel = new CityModel();

        cityModel.setIstat( cityCreateRequestDto.getIstat() );
        cityModel.setComune( cityCreateRequestDto.getComune() );
        cityModel.setRegione( cityCreateRequestDto.getRegione() );
        cityModel.setProvincia( cityCreateRequestDto.getProvincia() );
        cityModel.setPrefisso( cityCreateRequestDto.getPrefisso() );
        cityModel.setCodFisco( cityCreateRequestDto.getCodFisco() );
        cityModel.setSuperficie( cityCreateRequestDto.getSuperficie() );
        cityModel.setNumResidenti( cityCreateRequestDto.getNumResidenti() );
        cityModel.setGeoCoordinate( cityGeoCoordinateRequestDtoToGeoCoordinateModel( cityCreateRequestDto.getGeoCoordinate() ) );

        return cityModel;
    }

    @Override
    public CityModel fromUpdateRequestToModel(CityUpdateRequestDto cityUpdateRequestDto) {
        if ( cityUpdateRequestDto == null ) {
            return null;
        }

        CityModel cityModel = new CityModel();

        cityModel.setComune( cityUpdateRequestDto.getComune() );
        cityModel.setRegione( cityUpdateRequestDto.getRegione() );
        cityModel.setProvincia( cityUpdateRequestDto.getProvincia() );
        cityModel.setPrefisso( cityUpdateRequestDto.getPrefisso() );
        cityModel.setCodFisco( cityUpdateRequestDto.getCodFisco() );
        cityModel.setSuperficie( cityUpdateRequestDto.getSuperficie() );
        cityModel.setNumResidenti( cityUpdateRequestDto.getNumResidenti() );
        cityModel.setGeoCoordinate( cityGeoCoordinateRequestDtoToGeoCoordinateModel( cityUpdateRequestDto.getGeoCoordinate() ) );

        return cityModel;
    }

    protected GeoCoordinateModel geoCoordinateEntityToGeoCoordinateModel(GeoCoordinateEntity geoCoordinateEntity) {
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

    protected GeoCoordinateEntity geoCoordinateModelToGeoCoordinateEntity(GeoCoordinateModel geoCoordinateModel) {
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

    protected CityGeoCoordinateResponseDto geoCoordinateModelToCityGeoCoordinateResponseDto(GeoCoordinateModel geoCoordinateModel) {
        if ( geoCoordinateModel == null ) {
            return null;
        }

        CityGeoCoordinateResponseDto cityGeoCoordinateResponseDto = new CityGeoCoordinateResponseDto();

        cityGeoCoordinateResponseDto.setLng( geoCoordinateModel.getLng() );
        cityGeoCoordinateResponseDto.setLat( geoCoordinateModel.getLat() );

        return cityGeoCoordinateResponseDto;
    }

    protected GeoCoordinateModel cityGeoCoordinateRequestDtoToGeoCoordinateModel(CityGeoCoordinateRequestDto cityGeoCoordinateRequestDto) {
        if ( cityGeoCoordinateRequestDto == null ) {
            return null;
        }

        GeoCoordinateModel geoCoordinateModel = new GeoCoordinateModel();

        geoCoordinateModel.setLng( cityGeoCoordinateRequestDto.getLng() );
        geoCoordinateModel.setLat( cityGeoCoordinateRequestDto.getLat() );

        return geoCoordinateModel;
    }
}
