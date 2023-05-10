package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.message.ChatDetailsDto;
import com.dtu.kolgo.dto.message.ChatDto;
import com.dtu.kolgo.model.Chat;

import java.security.Principal;
import java.util.List;

public interface ChatService {

    ChatDetailsDto save(Principal principal, ChatDto dto);

    List<ChatDetailsDto> getAllDetailsByPrincipal(Principal principal);

    Chat getById(int id);

}
