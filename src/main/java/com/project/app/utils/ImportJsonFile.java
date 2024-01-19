package com.project.app.utils;

import com.project.app.entity.CityEntity;
import com.project.app.entity.GeoCoordinateEntity;
import com.project.app.enumeration.ErrorCode;
import com.project.app.exception.InternalErrorException;
import com.project.app.repository.CityRepository;
import com.project.app.repository.GeoCoordinatesRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.File;

@Service
public class ImportJsonFile {

    private final CityRepository cityRepository;

    private final GeoCoordinatesRepository geoCoordinatesRepository;

    private final ObjectMapper mapper;

    public ImportJsonFile(CityRepository cityRepository,
                          GeoCoordinatesRepository geoCoordinatesRepository) {
        this.cityRepository = cityRepository;
        this.geoCoordinatesRepository = geoCoordinatesRepository;
        mapper = new ObjectMapper();
    }

    @PostConstruct
    public void init() {
        loadCities();
        loadGeoCoordinates();
    }

    @Transactional
    public void loadCities() {

        try (JsonParser jsonParser = mapper.getFactory().createParser(new File("italy_cities.json"))) {

            if (jsonParser.nextToken() != JsonToken.START_ARRAY) {
                throw new InternalErrorException("content need to be an array", ErrorCode.ERROR_READING_JSON_FILE);
            }

            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {

                cityRepository.save(mapper.readValue(jsonParser, CityEntity.class));
            }
        } catch (Exception e) {
            throw generateInternalError(e);
        }
    }

    @Transactional
    public void loadGeoCoordinates() {

        try (JsonParser jsonParser = mapper.getFactory().createParser(new File("italy_geo.json"))) {

            if (jsonParser.nextToken() != JsonToken.START_ARRAY) {
                throw new InternalErrorException("content need to be an array", ErrorCode.ERROR_READING_JSON_FILE);
            }

            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {

                geoCoordinatesRepository.save(mapper.readValue(jsonParser, GeoCoordinateEntity.class));
            }
        } catch (Exception e) {
            throw generateInternalError(e);
        }
    }

    private InternalErrorException generateInternalError(Exception e) {
        return new InternalErrorException("Error reading JSON file", e, ErrorCode.ERROR_READING_JSON_FILE);
    }

}
