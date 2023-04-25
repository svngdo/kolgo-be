package com.dtu.kolgo.repository;

import com.dtu.kolgo.model.Message;
import com.dtu.kolgo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findAllByUser(User user);

}
