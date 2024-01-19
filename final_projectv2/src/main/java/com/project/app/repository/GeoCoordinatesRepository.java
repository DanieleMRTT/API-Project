package com.project.app.repository;

import com.project.app.entity.GeoCoordinateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeoCoordinatesRepository extends JpaRepository<GeoCoordinateEntity, Integer> {


    <S extends GeoCoordinateEntity> S save(S entity);

}
