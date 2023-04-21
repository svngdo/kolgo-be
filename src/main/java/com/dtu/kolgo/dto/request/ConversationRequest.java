package com.dtu.kolgo.dto.request;

import com.dtu.kolgo.util.constant.ConversationType;
import lombok.Data;

@Data
public class ConversationRequest {

    private ConversationType type;
    private Integer receiverId;

}
