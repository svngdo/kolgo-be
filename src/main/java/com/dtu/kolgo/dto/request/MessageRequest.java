package com.dtu.kolgo.dto.request;

import com.dtu.kolgo.util.constant.ConversationType;
import com.dtu.kolgo.util.constant.MessageType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageRequest {

    private Integer conversationId;
    private ConversationType conversationType;
    private Integer senderId;
    private Integer receiverId;
    private MessageType messageType;
    private String content;
    private LocalDateTime timestamp;

}
