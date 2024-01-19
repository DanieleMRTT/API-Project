package com.project.app.configuration;

import com.project.app.constant.Path;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public GroupedOpenApi cityOpenApiGroup(){
        return GroupedOpenApi.builder()
                .group("Cities")
                .displayName("cities")
                .packagesToScan("com.project.app")
                .pathsToMatch(Path.CITY + "/**",
                        Path.CITY_WITH_INHABITANTS_GREATER_THAN+ "/**",
                        Path.CITY_WITH_INHABITANTS_GREATER_THAN_IN_PROVINCE + "/**",
                        Path.CITY_WITH_INHABITANTS_GREATER_THAN_IN_REGION + "/**")
                .build();
    }

    @Bean
    public GroupedOpenApi weatherOpenApiGroup(){
        return GroupedOpenApi.builder()
                .group("Weather")
                .displayName("weather")
                .packagesToScan("com.project.app")
                .pathsToMatch(Path.WEATHER_FORECAST + "/**",
                        Path.DAILY_AVERAGE_TEMPERATURE_CITY + "/**",
                        Path.PROVINCE_AVERAGE_TEMPERATURE + "/**")
                .build();
    }
    @Bean
    public GroupedOpenApi geoCoordinateOpenApiGroup(){
        return GroupedOpenApi.builder()
                .group("GeoCoordinate")
                .displayName("geocoordinate")
                .packagesToScan("com.project.app")
                .pathsToMatch(Path.GEO_COORDINATE + "/**")
                .build();
    }

    @Bean
    public OpenAPI openAPIConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("Weather-City Services")
                        .description("A set of weather-city services")
                        .version("1.0.0")
                        .contact(new Contact()
                                .email("daniele@moretti.com")
                                .name("DM")));
    }
}
