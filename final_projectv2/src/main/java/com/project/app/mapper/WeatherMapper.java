package com.project.app.mapper;

import com.project.app.dto.WeatherResponseDto;
import com.project.app.model.WeatherModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface WeatherMapper {

    WeatherResponseDto fromModelToResponse(WeatherModel weatherModel);
}
