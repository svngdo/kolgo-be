package com.dtu.kolgo.repository;

import com.dtu.kolgo.model.Kol;
import com.dtu.kolgo.model.KolField;
import com.dtu.kolgo.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KolRepository extends JpaRepository<Kol, Integer> {

    Optional<Kol> findByUser(User user);

    List<Kol> findAllByField(KolField field);

    @Transactional
    void deleteByUser(User user);

}
