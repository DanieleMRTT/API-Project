package com.project.app.service;

import com.project.app.entity.CityEntity;
import com.project.app.enumeration.ErrorCode;
import com.project.app.exception.DataNotFoundException;
import com.project.app.exception.DataNotValidException;
import com.project.app.exception.EntityNotFoundException;
import com.project.app.exception.InternalErrorException;
import com.project.app.mapper.CityMapper;
import com.project.app.model.CityModel;
import com.project.app.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityService {

    private final CityRepository cityRepository;

    private final CityMapper cityMapper;


    public CityService(CityRepository cityRepository, CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    public List<CityModel> getCitiesWithNumberOfInhabitantsGreaterThan(int numberOfInhabitants) {

        try {
            return cityRepository.findByNumResidentiGreaterThan(numberOfInhabitants).stream()
                    .map(cityMapper::fromEntityToModel)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw generateInternalError(e);
        }

    }

    public List<CityModel> getTownsInGivenRegionWithNumberOfInhabitantsGreaterThan(String region, int numberOfInhabitants) {
        boolean regionExists;
        try {
            regionExists = cityRepository.existsByRegioneIgnoreCase(region);
        } catch (Exception e) {
            throw generateInternalError(e);
        }

        if(!regionExists){
            throw generateDataNotFound(region);
        }

        try {
            return cityRepository.findByRegioneAndNumResidentiGreaterThan(region, numberOfInhabitants).stream()
                    .map(cityMapper::fromEntityToModel)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw generateInternalError(e);
        }

    }

    public List<CityModel> getTownsInGivenProvinceWithNumberOfInhabitantsGreaterThan(String province, int numberOfInhabitants) {
        boolean provinceExists;
        try {
            provinceExists = cityRepository.existsByProvinciaIgnoreCase(province);
        } catch (Exception e) {
            throw generateInternalError(e);
        }

        if(!provinceExists){
            throw generateDataNotFound(province);
        }
        try {
            return cityRepository.findByProvinciaAndNumResidentiGreaterThan(province, numberOfInhabitants).stream()
                    .map(cityMapper::fromEntityToModel)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw generateInternalError(e);
        }
    }

    public CityModel readCity(int istatCode) {
        Optional<CityEntity> cityEntity;
        try {
            cityEntity = cityRepository.findById(istatCode);
        } catch (Exception e) {
            throw generateInternalError(e);
        }
        return cityEntity
                .map(cityMapper::fromEntityToModel)
                .orElseThrow(() -> generateEntityNotFound(istatCode));
    }

    public CityModel createCity(CityModel cityModel) {


        validateCityCreation(cityModel);

        CityEntity cityEntity;
        try {
            cityModel.getGeoCoordinate().setIstat(cityModel.getIstat());
            cityModel.getGeoCoordinate().setComune(cityModel.getComune());
            cityEntity = cityRepository.save(cityMapper.fromModelToEntity(cityModel));

        } catch (Exception e) {
            throw generateInternalError(e);
        }
        return cityMapper.fromEntityToModel(cityEntity);
    }

    public CityModel updateCity(CityModel cityModel, Integer istatCode) {
        Optional<CityEntity> cityEntity;
        try {
            cityEntity = cityRepository.findById(istatCode)
                    .map(entity -> {
                        Optional.ofNullable(cityModel.getIstat())
                                .ifPresent(entity::setIstat);
                        Optional.ofNullable(cityModel.getCodFisco())
                                .ifPresent(entity::setCodFisco);
                        Optional.ofNullable(cityModel.getComune())
                                .ifPresent(entity::setComune);
                        Optional.ofNullable(cityModel.getRegione())
                                .ifPresent(entity::setRegione);
                        Optional.ofNullable(cityModel.getProvincia())
                                .ifPresent(entity::setProvincia);
                        Optional.ofNullable(cityModel.getPrefisso())
                                .ifPresent(entity::setPrefisso);
                        Optional.ofNullable(cityModel.getSuperficie())
                                .ifPresent(entity::setSuperficie);
                        Optional.ofNullable(cityModel.getNumResidenti())
                                .ifPresent(entity::setNumResidenti);
                        if(cityModel.getGeoCoordinate().getLat() != null){
                            entity.getGeoCoordinate().setLat(cityModel.getGeoCoordinate().getLat());
                        }
                        if(cityModel.getGeoCoordinate().getLng() != null){
                            entity.getGeoCoordinate().setLng(cityModel.getGeoCoordinate().getLng());
                        }
                        return cityRepository.save(entity);
                    });
        } catch (Exception e) {
            throw generateInternalError(e);
        }
        return cityEntity
                .map(cityMapper:: fromEntityToModel)
                .orElseThrow(() -> generateEntityNotFound(istatCode));
    }

    private void validateCityCreation(CityModel cityModel) {
        boolean istatAlreadyPresent;
        boolean codFiscoAlreadyPresent;

        try {
            istatAlreadyPresent = cityRepository.findById(cityModel.getIstat()).isPresent();
            codFiscoAlreadyPresent = cityRepository.findByCodFisco(cityModel.getCodFisco()).isPresent();
        } catch (Exception e) {
            throw generateInternalError(e);
        }

        if (istatAlreadyPresent) {
            throw new DataNotValidException("Istat code already in use", ErrorCode.ISTAT_ALREADY_EXIST);
        }
        if (codFiscoAlreadyPresent) {
            throw new DataNotValidException("Tax code already in use", ErrorCode.TAX_CODE_ALREADY_EXIST);
        }
    }
    public void deleteById(int istatCode){
        boolean hasBeenDeleted;
        try {
            hasBeenDeleted = cityRepository.findById(istatCode)
                    .map(cityEntity -> {
                        cityRepository.delete(cityEntity);
                        return true;
                    })
                    .orElse(false);
        } catch (Exception e) {
            throw generateInternalError(e);
        }
        if (!hasBeenDeleted) {
            throw generateEntityNotFound(istatCode);
        }
    }



    private InternalErrorException generateInternalError(Exception e) {
        return new InternalErrorException("Something went wrong", e, ErrorCode.INTERNAL_ERROR);
    }

    private EntityNotFoundException generateEntityNotFound(int istatCode) {
        return new EntityNotFoundException("No data found for istat : " + istatCode, ErrorCode.DATA_NOT_FOUND);
    }

    private DataNotFoundException generateDataNotFound(String missingProperty) {
        return new DataNotFoundException("No data found for field : " + missingProperty, ErrorCode.DATA_NOT_FOUND);
    }



}
