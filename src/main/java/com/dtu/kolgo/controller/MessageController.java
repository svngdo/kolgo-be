package com.dtu.kolgo.controller;

import com.dtu.kolgo.dto.message.ChatMessageDto;
import com.dtu.kolgo.dto.message.NotificationDto;
import com.dtu.kolgo.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageService service;

    @MessageMapping("chats/public")
    @SendTo("public/messages")
    public ChatMessageDto handlePublicMessage(@Payload ChatMessageDto chatMessageDto) {
        service.handlePublicChatMessage(chatMessageDto);
        return chatMessageDto;
    }

    @MessageMapping("chats/private")
    public void handlePrivateMessage(@Payload ChatMessageDto chatMessageDto) {
        service.handlePrivateChatMessage(chatMessageDto);
    }

    @MessageMapping("notifications/public")
    public void handlePublicNotification(@Payload NotificationDto notificationDto) {
        System.out.println(notificationDto);
    }

    @MessageMapping("notifications/private")
    public void handleNotification(@Payload NotificationDto notificationDto) {
        service.handlePrivateNotification(notificationDto);
    }

}
