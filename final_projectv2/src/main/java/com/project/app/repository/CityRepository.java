package com.project.app.repository;

import com.project.app.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Integer> {

    @Override
    List<CityEntity> findAll();

    List<CityEntity> findByNumResidentiGreaterThan(int numberOfInhabitants);

    @Override
    <S extends CityEntity> S save(S entity);

    @Override
    Optional<CityEntity> findById(Integer istatCode);

    Optional<CityEntity> findByCodFisco(String codFisco);

    @Override
    void deleteById(Integer istat);

    List<CityEntity> findByProvincia(String province);

    List<CityEntity> findByRegioneAndNumResidentiGreaterThan(String region,int numberOfInhabitants);


    List<CityEntity> findByProvinciaAndNumResidentiGreaterThan(String province, int numberOfInhabitants);


    boolean existsByRegioneIgnoreCase(String region);

    boolean existsByProvinciaIgnoreCase(String province);
}
