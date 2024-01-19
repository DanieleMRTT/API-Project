package com.project.app;

import com.project.app.constant.Path;
import com.project.app.enumeration.ErrorCode;
import com.project.app.exception.DataNotFoundException;
import com.project.app.model.CityModel;
import com.project.app.service.CityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CityInhabitantsTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private CityService cityService;


    @Test
    void shouldSearchCitiesByInhabitants() throws Exception {

        int inhabitants = 2000000;

        when(cityService.getCitiesWithNumberOfInhabitantsGreaterThan(inhabitants))
                .thenReturn(Arrays.asList(new CityModel(),new CityModel()));

        mockMvc.perform(MockMvcRequestBuilders.post(
                                Path.CITY_WITH_INHABITANTS_GREATER_THAN + "/searchByInhabitants")
                        .param("numberOfInhabitants", String.valueOf(inhabitants)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath(("$.length()")).value(2));

        verify(cityService, times(1)).getCitiesWithNumberOfInhabitantsGreaterThan(inhabitants);
    }

    @Test
    void shouldSearchCitiesByInhabitantsAndRegion() throws Exception {

        int inhabitants = 20000;
        String region = "Lazio";

        when(cityService.getTownsInGivenRegionWithNumberOfInhabitantsGreaterThan(region,inhabitants))
                .thenReturn(Arrays.asList(new CityModel(),new CityModel(), new CityModel()));

        mockMvc.perform(MockMvcRequestBuilders.post(
                                Path.CITY_WITH_INHABITANTS_GREATER_THAN_IN_REGION + "/searchByInhabitantsAndRegion")
                        .param("numberOfInhabitants", String.valueOf(inhabitants))
                        .param("region", region))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath(("$.length()")).value(3));

        verify(cityService, times(1)).getTownsInGivenRegionWithNumberOfInhabitantsGreaterThan(region,inhabitants);
    }

    @Test
    void shouldSearchCitiesByInhabitantsAndRegionCaseInsensitive() throws Exception {

        int inhabitants = 20000;
        String region = "LAZIO";

        when(cityService.getTownsInGivenRegionWithNumberOfInhabitantsGreaterThan(region,inhabitants))
                .thenReturn(Arrays.asList(new CityModel(),new CityModel(), new CityModel()));

        mockMvc.perform(MockMvcRequestBuilders.post(
                                Path.CITY_WITH_INHABITANTS_GREATER_THAN_IN_REGION + "/searchByInhabitantsAndRegion")
                        .param("numberOfInhabitants", String.valueOf(inhabitants))
                        .param("region", region))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath(("$.length()")).value(3));

        verify(cityService, times(1)).getTownsInGivenRegionWithNumberOfInhabitantsGreaterThan(region,inhabitants);
    }

    @Test
    void searchCitiesByInhabitantsAndRegionShouldGenerateDataNotFound() throws Exception {

        int inhabitants = 20000;
        String wrongRegion = "notARegion";

        when(cityService.getTownsInGivenRegionWithNumberOfInhabitantsGreaterThan(wrongRegion,inhabitants))
                .thenThrow(new DataNotFoundException("No data found for field : " + wrongRegion, ErrorCode.DATA_NOT_FOUND));

        mockMvc.perform(MockMvcRequestBuilders.post(
                                Path.CITY_WITH_INHABITANTS_GREATER_THAN_IN_REGION + "/searchByInhabitantsAndRegion")
                        .param("numberOfInhabitants", String.valueOf(inhabitants))
                        .param("region", wrongRegion))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode", Matchers.is(ErrorCode.DATA_NOT_FOUND.toString())));

        verify(cityService, times(1)).getTownsInGivenRegionWithNumberOfInhabitantsGreaterThan(wrongRegion,inhabitants);
    }

    @Test
    void shouldSearchCitiesByInhabitantsAndProvince() throws Exception {

        int inhabitants = 20000;
        String province = "TO";

        when(cityService.getTownsInGivenProvinceWithNumberOfInhabitantsGreaterThan(province,inhabitants))
                .thenReturn(List.of(new CityModel()));

        mockMvc.perform(MockMvcRequestBuilders.post(
                                Path.CITY_WITH_INHABITANTS_GREATER_THAN_IN_PROVINCE + "/searchByInhabitantsAndProvince")
                        .param("numberOfInhabitants", String.valueOf(inhabitants))
                        .param("province", province))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath(("$.length()")).value(1));

        verify(cityService, times(1)).getTownsInGivenProvinceWithNumberOfInhabitantsGreaterThan(province,inhabitants);
    }

    @Test
    void shouldSearchCitiesByInhabitantsAndProvinceCaseInsensitive() throws Exception {

        int inhabitants = 20000;
        String province = "to";

        when(cityService.getTownsInGivenProvinceWithNumberOfInhabitantsGreaterThan(province,inhabitants))
                .thenReturn(List.of(new CityModel()));

        mockMvc.perform(MockMvcRequestBuilders.post(
                                Path.CITY_WITH_INHABITANTS_GREATER_THAN_IN_PROVINCE + "/searchByInhabitantsAndProvince")
                        .param("numberOfInhabitants", String.valueOf(inhabitants))
                        .param("province", province))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath(("$.length()")).value(1));

        verify(cityService, times(1)).getTownsInGivenProvinceWithNumberOfInhabitantsGreaterThan(province,inhabitants);
    }
    @Test
    void searchCitiesByInhabitantsAndProvinceShouldGenerateDataNotFound() throws Exception {

        int inhabitants = 20000;
        String wrongProvince = "notAProvince";

        when(cityService.getTownsInGivenProvinceWithNumberOfInhabitantsGreaterThan(wrongProvince,inhabitants))
                .thenThrow(new DataNotFoundException("No data found for field : " + wrongProvince, ErrorCode.DATA_NOT_FOUND));

        mockMvc.perform(MockMvcRequestBuilders.post(
                                Path.CITY_WITH_INHABITANTS_GREATER_THAN_IN_PROVINCE + "/searchByInhabitantsAndProvince")
                        .param("numberOfInhabitants", String.valueOf(inhabitants))
                        .param("province", wrongProvince))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode", Matchers.is(ErrorCode.DATA_NOT_FOUND.toString())));

        verify(cityService, times(1)).getTownsInGivenProvinceWithNumberOfInhabitantsGreaterThan(wrongProvince,inhabitants);
    }
}
