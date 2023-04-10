package com.dtu.kolgo.repository;

import com.dtu.kolgo.model.KolField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KolFieldRepository extends JpaRepository<KolField, Short> {
}
