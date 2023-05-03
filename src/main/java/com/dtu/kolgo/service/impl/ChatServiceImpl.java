package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.ChatDto;
import com.dtu.kolgo.dto.request.MessageRequest;
import com.dtu.kolgo.dto.response.MessageResponse;
import com.dtu.kolgo.model.Conversation;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.repository.ChatRepository;
import com.dtu.kolgo.service.ChatService;
import com.dtu.kolgo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository conversationRepo;
    private final UserService userService;

    @Override
    public ChatDto addConversation(Principal principal, ChatDto dto) {

        User sender = userService.get(principal);
        User receiver = userService.get(dto.getReceiverId());
        List<User> users = new ArrayList<>();
        users.add(sender);
        users.add(receiver);

        Conversation conversation = conversationRepo.save(new Conversation(
                dto.getType(),
                users,
                new ArrayList<>()));

        return ChatDto.builder()
                .id(conversation.getId())
                .type(conversation.getType())
                .receiverId(receiver.getId())
                .receiverFirstName(receiver.getFirstName())
                .receiverLastName(receiver.getLastName())
                .messages(new ArrayList<>())
                .build();
    }

    @Override
    public List<ChatDto> getConversations(Principal principal) {
        User sender = userService.get(principal);
        List<Conversation> conversations = conversationRepo.findAllByUsersContains(sender);
        return conversations.stream().map(c ->
        {
            User receiver =
                    c.getUsers().stream()
                            .filter(u -> !u.getId().equals(sender.getId())).toList().get(0);
            List<MessageResponse> messages =
                    c.getMessages().stream().map(msg -> MessageResponse.builder()
                            .authorId(msg.getUser().getId())
                            .authorFirstName(msg.getUser().getFirstName())
                            .authorLastName(msg.getUser().getLastName())
                            .messageType(msg.getType())
                            .content(msg.getContent())
                            .timestamp(msg.getTimestamp())
                            .conversationId(msg.getConversation().getId())
                            .build()).toList();
            return ChatDto.builder()
                    .id(c.getId())
                    .type(c.getType())
                    .receiverId(receiver.getId())
                    .receiverFirstName(receiver.getFirstName())
                    .receiverLastName(receiver.getLastName())
                    .messages(messages)
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public MessageResponse handlePublicMessage(MessageRequest request) {
        User author = userService.get(request.getSenderId());
        return MessageResponse.builder()
                .authorId(author.getId())
                .authorFirstName(author.getFirstName())
                .authorLastName(author.getLastName())
                .messageType(request.getMessageType())
                .content(request.getContent())
                .timestamp(request.getTimestamp())
                .build();
    }

    @Override
    public MessageResponse handlePrivateMessage(MessageRequest request) {
        User sender = userService.get(request.getSenderId());
        return MessageResponse.builder()
                .conversationId(request.getConversationId())
                .authorId(request.getSenderId())
                .authorFirstName(sender.getFirstName())
                .authorLastName(sender.getLastName())
                .messageType(request.getMessageType())
                .content(request.getContent())
                .timestamp(request.getTimestamp())
                .build();
    }

}
