package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.message.ChatMessageDto;
import com.dtu.kolgo.dto.message.MessageDto;
import com.dtu.kolgo.dto.message.NotificationDto;
import com.dtu.kolgo.enums.MessageType;
import com.dtu.kolgo.service.ChatMessageService;
import com.dtu.kolgo.service.ChatService;
import com.dtu.kolgo.service.MessageService;
import com.dtu.kolgo.service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ModelMapper mapper;

    @Override
    public void handlePublicMessage(MessageDto dto) {
        try {
//            ObjectMapper mapper = new ObjectMapper();
//            ChatMessageDto chatMessageDto = mapper.readValue(dto.getChatMessage(), ChatMessageDto.class);
//            System.out.println(chatMessageDto);
//            chatMessageService.save(chatMessageDto);
        } catch (Exception e) {
            log.error("Message Service Impl Exception", e);
        }
    }

    @Override
    public void handlePrivateMessage(MessageDto dto) {
        System.out.println(dto);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            if (dto.getType() == MessageType.CHAT_MESSAGE) {
                ChatMessageDto chatMessageDto = mapper.map(chatMessageService.save(dto.getChatMessage()), ChatMessageDto.class);
                dto.setChatMessage(chatMessageDto);
            } else if (dto.getType() == MessageType.NOTIFICATION) {
                NotificationDto notificationDto = mapper.map(notificationService.save(dto.getNotification()), NotificationDto.class);
                dto.setNotification(notificationDto);
            }
            dto.getReceiverIds().forEach(id -> {
                simpMessagingTemplate.convertAndSendToUser(
                        id.toString(),
                        "messages",
                        dto
                );
            });
        } catch (Exception e) {
            log.error("Message Service Impl Exception", e);
        }


    }

    public void forwardNotification(NotificationDto dto) {

    }

}
