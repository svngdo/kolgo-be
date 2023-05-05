package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.ChatDto;
import com.dtu.kolgo.dto.request.MessageRequest;
import com.dtu.kolgo.dto.response.MessageResponse;

import java.security.Principal;
import java.util.List;

public interface ChatService {

    ChatDto addConversation(Principal principal, ChatDto dto);

    List<ChatDto> getConversations(Principal principal);

    MessageResponse handlePublicMessage(MessageRequest request);

    MessageResponse handlePrivateMessage(MessageRequest request);

}
