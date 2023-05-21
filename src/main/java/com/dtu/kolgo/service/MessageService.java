package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.message.ChatMessageDto;
import com.dtu.kolgo.dto.message.NotificationDto;

public interface MessageService {

    void handlePublicChatMessage(ChatMessageDto chatMessageDto);

    void handlePrivateChatMessage(ChatMessageDto chatMessageDto);

    void handlePrivateNotification(NotificationDto notificationDto);

}
