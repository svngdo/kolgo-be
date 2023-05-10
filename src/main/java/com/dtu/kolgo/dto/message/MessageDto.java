package com.dtu.kolgo.dto.message;

import com.dtu.kolgo.enums.MessageType;
import lombok.Data;

import java.util.List;

@Data
public class MessageDto {

    private MessageType type;
    private NotificationDto notification;
    private ChatMessageDto chatMessage;
    private Integer senderId;
    private List<Integer> receiverIds;

}
