package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.message.ChatMessageDto;
import com.dtu.kolgo.model.Chat;
import com.dtu.kolgo.model.ChatMessage;
import com.dtu.kolgo.repository.ChatMessageRepository;
import com.dtu.kolgo.service.ChatMessageService;
import com.dtu.kolgo.service.ChatService;
import com.dtu.kolgo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatMessageRepository repo;
    private final UserService userService;
    private final ChatService chatService;
    private final ModelMapper mapper;

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        return repo.save(chatMessage);
    }

    @Override
    public List<ChatMessageDto> getAllByChatId(int id) {
        Chat chat = chatService.getById(id);
        return repo.findAllByChat(chat).stream().map(
                chatMsg -> mapper.map(chatMsg, ChatMessageDto.class)
        ).toList();
    }

}