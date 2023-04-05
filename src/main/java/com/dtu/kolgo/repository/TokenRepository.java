package com.dtu.kolgo.repository;

import com.dtu.kolgo.model.Token;
import com.dtu.kolgo.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    boolean existsByValue(String value);

    @Transactional
    void deleteByValue(String value);

    @Transactional
    void deleteAllByUser(User user);

}
