package com.dtu.kolgo.dto;

import com.dtu.kolgo.dto.response.MessageResponse;
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
//    private Integer senderId;
//    private String senderFirstName;
//    private String senderLastName;
    private Integer receiverId;
    private String receiverFirstName;
    private String receiverLastName;
    private List<MessageResponse> messages;

}
