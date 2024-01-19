package com.project.app.service;

import com.project.app.entity.GeoCoordinateEntity;
import com.project.app.enumeration.ErrorCode;
import com.project.app.exception.EntityNotFoundException;
import com.project.app.exception.InternalErrorException;
import com.project.app.mapper.GeoCoordinateMapper;
import com.project.app.model.GeoCoordinateModel;
import com.project.app.repository.GeoCoordinatesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GeoCoordinateService {
    
    private final GeoCoordinatesRepository geoCoordinateRepository;

    private final GeoCoordinateMapper geoCoordinateMapper;


    public GeoCoordinateService(GeoCoordinatesRepository geoCoordinateRepository, GeoCoordinateMapper geoCoordinateMapper) {
        this.geoCoordinateRepository = geoCoordinateRepository;
        this.geoCoordinateMapper = geoCoordinateMapper;
    }

    public GeoCoordinateModel readGeoCoordinate(int istatCode) {
        Optional<GeoCoordinateEntity> geoCoordinatesRepositoryEntity;
        try {
            geoCoordinatesRepositoryEntity = geoCoordinateRepository.findById(istatCode);
        } catch (Exception e) {
            throw generateInternalError(e);
        }
        return geoCoordinatesRepositoryEntity
                .map(geoCoordinateMapper::fromEntityToModel)
                .orElseThrow(() -> generateEntityNotFound(istatCode));
    }

    public GeoCoordinateModel createGeoCoordinate(GeoCoordinateModel geoCoordinateModel) {

        validateGeoCoordinateCreation(geoCoordinateModel);

        GeoCoordinateEntity geoCoordinateEntity;
        try {
            geoCoordinateModel.setIstat(geoCoordinateModel.getIstat());
            geoCoordinateModel.setComune(geoCoordinateModel.getComune());
            
            geoCoordinateEntity = geoCoordinateRepository.save(geoCoordinateMapper.fromModelToEntity(geoCoordinateModel));

        } catch (Exception e) {
            throw generateInternalError(e);
        }
        return geoCoordinateMapper.fromEntityToModel(geoCoordinateEntity);
    }

    private void validateGeoCoordinateCreation(GeoCoordinateModel geoCoordinateModel) {
        boolean istatAlreadyPresent;

        try {
            istatAlreadyPresent = geoCoordinateRepository.findById(geoCoordinateModel.getIstat()).isPresent();
            
        } catch (Exception e) {
            throw generateInternalError(e);
        }

        if (!istatAlreadyPresent) {
            throw generateEntityNotFound(geoCoordinateModel.getIstat());
        }
        
    }

    public void deleteById(int istatCode){
        boolean hasBeenDeleted;
        try {
            hasBeenDeleted = geoCoordinateRepository.findById(istatCode)
                    .map(geoCoordinateEntity -> {
                        geoCoordinateRepository.delete(geoCoordinateEntity);
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
        return new EntityNotFoundException("No data found for istat " + istatCode, ErrorCode.DATA_NOT_FOUND);
    }
}
