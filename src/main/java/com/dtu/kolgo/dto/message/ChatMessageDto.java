package com.dtu.kolgo.dto.message;

import com.dtu.kolgo.dto.user.UserDto;
import com.dtu.kolgo.enums.ChatMessageType;
import com.dtu.kolgo.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatMessageDto {

    private UserDto user;
    private ChatMessageType type;
    private String timestamp;
    private String content;
    private Integer chatId;

}