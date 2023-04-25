package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.ConversationRequest;
import com.dtu.kolgo.dto.request.MessageRequest;
import com.dtu.kolgo.dto.response.ConversationResponse;
import com.dtu.kolgo.dto.response.MessageResponse;

import java.security.Principal;
import java.util.List;

public interface ChatService {

    ConversationResponse addConversation(Principal principal, ConversationRequest request);

    List<ConversationResponse> getConversations(Principal principal);

    MessageResponse handlePublicMessage(MessageRequest request);

    MessageResponse handlePrivateMessage(MessageRequest request);

}
