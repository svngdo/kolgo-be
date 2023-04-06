package com.dtu.kolgo.repository;

import com.dtu.kolgo.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Short> {

    Optional<Gender> findByName(String name);

}
