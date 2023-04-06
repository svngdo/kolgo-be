package com.dtu.kolgo.repository;

import com.dtu.kolgo.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Short> {

    Optional<City> findByName(String name);

    Optional<City> findByAbbreviation(String abbreviation);

}
