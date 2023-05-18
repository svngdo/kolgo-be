package com.dtu.kolgo.repository;

import com.dtu.kolgo.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    Image findByName(String name);

    boolean existsByName(String name);

}
