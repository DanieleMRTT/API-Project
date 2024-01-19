package com.project.app.mapper;

import com.project.app.dto.WeatherResponseDto;
import com.project.app.model.WeatherModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-19T12:56:04+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.4.1 (Oracle Corporation)"
)
@Component
public class WeatherMapperImpl implements WeatherMapper {

    @Override
    public WeatherResponseDto fromModelToResponse(WeatherModel weatherModel) {
        if ( weatherModel == null ) {
            return null;
        }

        WeatherResponseDto weatherResponseDto = new WeatherResponseDto();

        weatherResponseDto.setLatitude( weatherModel.getLatitude() );
        weatherResponseDto.setLongitude( weatherModel.getLongitude() );
        weatherResponseDto.setHourly( weatherModel.getHourly() );
        weatherResponseDto.setTimezone( weatherModel.getTimezone() );

        return weatherResponseDto;
    }
}
