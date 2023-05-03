package com.dtu.kolgo.dto.request;

import com.dtu.kolgo.enums.ChatType;
import lombok.Data;

@Data
public class ConversationRequest {

    private ChatType type;
    private Integer receiverId;

}
