package com.dtu.kolgo.dto.request;

import com.dtu.kolgo.enums.ConversationType;
import lombok.Data;

@Data
public class ConversationRequest {

    private ConversationType type;
    private Integer receiverId;

}
