package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.message.ChatMessageDto;
import com.dtu.kolgo.dto.message.NotificationDto;
import com.dtu.kolgo.enums.NotificationStatus;
import com.dtu.kolgo.model.Chat;
import com.dtu.kolgo.model.ChatMessage;
import com.dtu.kolgo.model.Notification;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.service.*;
import com.dtu.kolgo.util.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageServiceImpl implements MessageService {

    private final ChatService chatService;
    private final ChatMessageService chatMessageService;
    private final NotificationService notificationService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final UserService userService;
    private final ModelMapper mapper;

    @Override
    public void handlePublicChatMessage(ChatMessageDto chatMessageDto) {
        System.out.println(chatMessageDto);
    }

    @Override
    public void handlePrivateChatMessage(ChatMessageDto chatMessageDto) {
        System.out.println(chatMessageDto);
        User user = userService.getById(chatMessageDto.getUserId());
        Chat chat = chatService.getById(chatMessageDto.getChatId());
        ChatMessage chatMessage = chatMessageService.save(new ChatMessage(
                chatMessageDto.getType(),
                DateTimeUtils.convertToLocalDateTime(chatMessageDto.getTimestamp()),
                chatMessageDto.getContent(),
                user,
                chat
        ));
        chatMessageDto.setId(chatMessage.getId());
        System.out.println(chatMessage);
        chat.getChatMessages().add(chatMessage);
        chatService.save(chat);

        chat.getUserIds().forEach(id -> {
            simpMessagingTemplate.convertAndSendToUser(
                    id.toString(),
                    "messages",
                    chatMessageDto
            );
        });
    }

    @Override
    public void handlePrivateNotification(NotificationDto notificationDto) {
        System.out.println(notificationDto);
        User user = userService.getById(notificationDto.getUserId());

        Notification notification = notificationService.save(new Notification(
                notificationDto.getType(),
                notificationDto.getContent(),
                NotificationStatus.UNREAD,
                DateTimeUtils.convertToLocalDateTime(notificationDto.getTimestamp()),
                notificationDto.getBookingId(),
                user
        ));

        notificationDto.setId(notification.getId());
        notificationDto.setStatus(notification.getStatus());
        notificationDto.setUserFirstName(user.getFirstName());
        notificationDto.setUserLastName(user.getLastName());
        notificationDto.setUserAvatar(user.getAvatar());

        System.out.println(notificationDto);

        simpMessagingTemplate.convertAndSendToUser(
                user.getId().toString(),
                "notifications",
                notificationDto
        );
    }

}
