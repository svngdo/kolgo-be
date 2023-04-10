package com.dtu.kolgo.repository;

import com.dtu.kolgo.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Short> {
}
