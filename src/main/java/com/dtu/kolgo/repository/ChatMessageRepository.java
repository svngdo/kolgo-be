package com.dtu.kolgo.repository;

import com.dtu.kolgo.model.Chat;
import com.dtu.kolgo.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {

    List<ChatMessage> findAllByChat(Chat chat);

}
