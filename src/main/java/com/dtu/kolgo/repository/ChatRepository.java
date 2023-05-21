package com.dtu.kolgo.repository;

import com.dtu.kolgo.model.Chat;
import com.dtu.kolgo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {

    List<Chat> findAllByUsersContains(User user);

    boolean existsByUsersContains(User user);

}
