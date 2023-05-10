package com.dtu.kolgo.dto.message;

import com.dtu.kolgo.dto.user.UserDto;
import com.dtu.kolgo.enums.ChatType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatDetailsDto {

    private Integer id;
    private ChatType type;
    private String date;
    private UserDto user;
    private List<UserDto> users;
    private List<ChatMessageDto> messages;


}
