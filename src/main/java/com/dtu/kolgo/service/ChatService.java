package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.message.ChatDto;
import com.dtu.kolgo.model.Chat;

import java.security.Principal;
import java.util.List;

public interface ChatService {

    Chat save(Chat chat);

    ChatDto save(Principal principal, ChatDto dto);

    Chat getById(int id);

    List<ChatDto> getDtosByPrincipal(Principal principal);

    ChatDto getDtoById(int id);
}
