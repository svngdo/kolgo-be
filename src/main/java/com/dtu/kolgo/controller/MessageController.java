package com.dtu.kolgo.controller;

import com.dtu.kolgo.dto.message.MessageDto;
import com.dtu.kolgo.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageService service;

    @MessageMapping("public")
    @SendTo("public/messages")
    public MessageDto handlePublicMessage(@Payload MessageDto dto) {
        service.handlePublicMessage(dto);
        return dto;
    }

    @MessageMapping("private")
    public void handlePrivateMessage(@Payload MessageDto dto) {
        System.out.println(dto);
        service.handlePrivateMessage(dto);
    }

}
