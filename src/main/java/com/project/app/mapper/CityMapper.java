package com.project.app.mapper;

import com.project.app.dto.CityCreateRequestDto;
import com.project.app.dto.CityResponseDto;
import com.project.app.dto.CityUpdateRequestDto;
import com.project.app.entity.CityEntity;
import com.project.app.model.CityModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel =  MappingConstants.ComponentModel.SPRING)
public interface CityMapper {


   CityModel fromEntityToModel(CityEntity cityEntity);

   CityEntity fromModelToEntity(CityModel cityModel);

   CityResponseDto fromModelToResponse(CityModel cityModel);

    CityModel fromCreateRequestToModel(CityCreateRequestDto cityCreateRequestDto);
    CityModel fromUpdateRequestToModel(CityUpdateRequestDto cityUpdateRequestDto);
}
