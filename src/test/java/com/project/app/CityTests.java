package com.project.app;

import com.project.app.constant.Path;
import com.project.app.dto.CityGeoCoordinateResponseDto;
import com.project.app.dto.CityResponseDto;
import com.project.app.enumeration.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CityTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void shouldReadACity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(Path.CITY+ "/1200"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath(("$.comune"), Matchers.is("Porte")))
                .andReturn();
    }

    @Test
    void shouldReturnNoCityAndNotFound() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(Path.CITY + "/12"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateACity() throws Exception {
        CityResponseDto cityResponseDto = new CityResponseDto();
        cityResponseDto.setIstat(1);
        cityResponseDto.setComune("noName");
        cityResponseDto.setPrefisso("noName");
        cityResponseDto.setProvincia("no");
        cityResponseDto.setRegione("noName");
        cityResponseDto.setCodFisco("2w12");
        cityResponseDto.setSuperficie(2.3);
        cityResponseDto.setNumResidenti(2);
        CityGeoCoordinateResponseDto geoCoordinateResponseDto = new CityGeoCoordinateResponseDto();
        geoCoordinateResponseDto.setLat("1.2");
        geoCoordinateResponseDto.setLng("1.2");
        cityResponseDto.setGeoCoordinate(geoCoordinateResponseDto);

        mockMvc.perform(MockMvcRequestBuilders.post(Path.CITY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cityResponseDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.comune", Matchers.is(cityResponseDto.getComune())));
    }

    @Test
    void shouldGenerateBadRequestForMissingPropertiesDataOnCreation() throws Exception {
        CityResponseDto cityResponseDto = new CityResponseDto();
        cityResponseDto.setIstat(1);
        cityResponseDto.setComune("noName");

        mockMvc.perform(MockMvcRequestBuilders.post(Path.CITY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cityResponseDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", Matchers.is(ErrorCode.DATA_NOT_VALID.toString())));
    }

    @Test
    void shouldGenerateBadRequestForIstatAlreadyPresent() throws Exception {
        CityResponseDto cityResponseDto = new CityResponseDto();
        cityResponseDto.setIstat(1001);
        cityResponseDto.setComune("noName");
        cityResponseDto.setPrefisso("noName");
        cityResponseDto.setProvincia("no");
        cityResponseDto.setRegione("noName");
        cityResponseDto.setCodFisco("2w12");
        cityResponseDto.setSuperficie(2.3);
        cityResponseDto.setNumResidenti(2);
        CityGeoCoordinateResponseDto geoCoordinateResponseDto = new CityGeoCoordinateResponseDto();
        geoCoordinateResponseDto.setLat("1.2");
        geoCoordinateResponseDto.setLng("1.2");
        cityResponseDto.setGeoCoordinate(geoCoordinateResponseDto);

        mockMvc.perform(MockMvcRequestBuilders.post(Path.CITY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cityResponseDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", Matchers.is(ErrorCode.ISTAT_ALREADY_EXIST.toString())));
    }
    @Test
    void shouldGenerateBadRequestForCodFiscoAlreadyPresent() throws Exception {
        CityResponseDto cityResponseDto = new CityResponseDto();
        cityResponseDto.setIstat(2);
        cityResponseDto.setComune("noName");
        cityResponseDto.setPrefisso("noName");
        cityResponseDto.setProvincia("no");
        cityResponseDto.setRegione("noName");
        cityResponseDto.setCodFisco("A117");
        cityResponseDto.setSuperficie(2.3);
        cityResponseDto.setNumResidenti(2);
        CityGeoCoordinateResponseDto geoCoordinateResponseDto = new CityGeoCoordinateResponseDto();
        geoCoordinateResponseDto.setLat("1.2");
        geoCoordinateResponseDto.setLng("1.2");
        cityResponseDto.setGeoCoordinate(geoCoordinateResponseDto);

        mockMvc.perform(MockMvcRequestBuilders.post(Path.CITY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cityResponseDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", Matchers.is(ErrorCode.TAX_CODE_ALREADY_EXIST.toString())));
    }



    @Test
    void shouldUpdateCity() throws Exception {

        CityResponseDto cityResponseDto = new CityResponseDto();
        cityResponseDto.setIstat(1011);
        cityResponseDto.setComune("Milano");
        cityResponseDto.setPrefisso("noName");
        cityResponseDto.setProvincia("no");
        cityResponseDto.setRegione("noName");
        cityResponseDto.setCodFisco("2w12");
        cityResponseDto.setSuperficie(2.3);
        cityResponseDto.setNumResidenti(2);
        CityGeoCoordinateResponseDto geoCoordinateResponseDto = new CityGeoCoordinateResponseDto();
        geoCoordinateResponseDto.setLat("1.6");
        geoCoordinateResponseDto.setLng("1.6");
        cityResponseDto.setGeoCoordinate(geoCoordinateResponseDto);
        mockMvc.perform(MockMvcRequestBuilders.put(Path.CITY + "/1011",cityResponseDto.getIstat())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cityResponseDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comune", Matchers.is(cityResponseDto.getComune())));
    }
    @Test
    void shouldDeleteACity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(Path.CITY + "/1011"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
    @Test
    void shouldDeleteACityShouldGenerateDataNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(Path.CITY + "/0"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode", Matchers.is(ErrorCode.DATA_NOT_FOUND.toString())));;
    }

}
