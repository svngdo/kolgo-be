package com.dtu.kolgo.repository;

import com.dtu.kolgo.enums.FieldType;
import com.dtu.kolgo.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FieldRepository extends JpaRepository<Field, Short> {

    List<Field> findAllByType(FieldType type);

    Optional<Field> findByName(String name);

}
