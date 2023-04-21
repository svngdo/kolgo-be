package com.dtu.kolgo.dto.response;

import com.dtu.kolgo.util.constant.ConversationType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConversationResponse {

    private Integer id;
    private ConversationType type;
//    private Integer senderId;
//    private String senderFirstName;
//    private String senderLastName;
    private Integer receiverId;
    private String receiverFirstName;
    private String receiverLastName;
    private List<MessageResponse> messages;

}
