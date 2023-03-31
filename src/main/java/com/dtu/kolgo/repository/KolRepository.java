package com.dtu.kolgo.repository;

import com.dtu.kolgo.model.Kol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KolRepository extends JpaRepository<Kol, Integer> {
}
