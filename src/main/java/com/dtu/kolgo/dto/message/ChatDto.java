package com.dtu.kolgo.dto.message;

import com.dtu.kolgo.dto.user.UserDto;
import com.dtu.kolgo.enums.ChatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ChatDto {

    private Integer id;
    private ChatType type;
    private String timestamp;
    private UserDto user;
    private List<Integer> userIds;
    private List<UserDto> users;
    private List<ChatMessageDto> chatMessages;

}
