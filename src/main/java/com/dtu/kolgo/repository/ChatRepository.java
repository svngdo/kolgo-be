package com.dtu.kolgo.repository;

import com.dtu.kolgo.model.Conversation;
import com.dtu.kolgo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Conversation, Integer> {

    List<Conversation> findAllByUsersContains(User user);

}
