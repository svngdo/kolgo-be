package com.dtu.kolgo.repository;

import com.dtu.kolgo.model.Enterprise;
import com.dtu.kolgo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Integer> {

    Optional<Enterprise> findByUser(User user);

}
