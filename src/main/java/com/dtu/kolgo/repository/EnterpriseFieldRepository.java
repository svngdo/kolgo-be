package com.dtu.kolgo.repository;

import com.dtu.kolgo.model.EnterpriseField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseFieldRepository extends JpaRepository<EnterpriseField, Short> {
}
