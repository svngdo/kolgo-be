package com.dtu.kolgo.repository;

import com.dtu.kolgo.model.Token;
import com.dtu.kolgo.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

    Optional<Token> findByValue(String value);

    Optional<Token> findByUser(User user);

    boolean existsByValue(String value);

    @Transactional
    void deleteByValue(String value);

    @Transactional
    void deleteAllByUser(User user);

}
