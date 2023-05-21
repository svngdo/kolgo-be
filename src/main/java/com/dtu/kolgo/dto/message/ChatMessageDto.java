package com.dtu.kolgo.dto.message;

import com.dtu.kolgo.enums.ChatMessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatMessageDto {

    private Integer id;
    private ChatMessageType type;
    private String timestamp;
    private String content;
    private Integer userId;
    private String userFirstName;
    private String userLastName;
    private String userAvatar;
    private Integer chatId;

}