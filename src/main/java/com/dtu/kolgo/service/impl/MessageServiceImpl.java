package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.message.MessageDto;
import com.dtu.kolgo.dto.message.NotificationDto;
import com.dtu.kolgo.enums.NotificationStatus;
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
    private final BookingService bookingService;
    private final UserService userService;
    private final ModelMapper mapper;

    @Override
    public void handlePublicMessage(MessageDto dto) {
        try {
            System.out.println(dto);
        } catch (Exception e) {
            log.error("Message Service Impl Exception", e);
        }
    }

    @Override
    public void handlePrivateMessage(MessageDto dto) {
//        System.out.println(dto);
//        try {
//            if (dto.getType() == MessageType.CHAT_MESSAGE) {
//                ChatMessageDto chatMessageDto = mapper.map(chatMessageService.save(dto.getChatMessage()), ChatMessageDto.class);
//                dto.setChatMessage(chatMessageDto);
//            dto.getReceiverIds().forEach(id -> {
//                simpMessagingTemplate.convertAndSendToUser(
//                        id.toString(),
//                        "messages",
//                        dto
//                );
//            });
//        } catch (Exception e) {
//            log.error("Message Service Impl Exception", e);
//        }
//
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
