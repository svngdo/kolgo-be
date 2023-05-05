package com.dtu.kolgo.controller;

import com.dtu.kolgo.dto.ChatDto;
import com.dtu.kolgo.dto.request.MessageRequest;
import com.dtu.kolgo.dto.response.MessageResponse;
import com.dtu.kolgo.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService service;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("public") // /api/chat/public
    @SendTo("public/messages")
    public MessageResponse handlePublicChat(@Payload MessageRequest request) {
        System.out.println(request);
        return service.handlePublicMessage(request);
    }

    @MessageMapping("private")
    public MessageResponse handlePrivateMessage(@Payload MessageRequest request) {
        MessageResponse msg = service.handlePrivateMessage(request);
        simpMessagingTemplate.convertAndSendToUser(
                request.getReceiverId().toString(),
                "messages",
                msg
        );
        System.out.println(request);
        return msg;
    }

    @GetMapping("conversations")
    public ResponseEntity<?> getUserChannels(
            Principal principal
    ) {
        return new ResponseEntity<>(
                service.getConversations(principal),
                HttpStatus.OK
        );
    }

    @PostMapping("conversations")
    public ResponseEntity<?> addConversation(
            Principal principal,
            @RequestBody @Valid ChatDto dto
    ) {
        return new ResponseEntity<>(
                service.addConversation(principal, dto),
                HttpStatus.OK
        );
    }

}
