package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.message.ChatMessageDto;
import com.dtu.kolgo.model.ChatMessage;

import java.util.List;

public interface ChatMessageService {

    ChatMessage save(ChatMessage chatMessage);

    List<ChatMessageDto> getAllByChatId(int id);

}
