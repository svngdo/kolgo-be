package com.dtu.kolgo.dto.response;

import com.dtu.kolgo.enums.MessageType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageResponse {

    private Integer authorId;
    private String authorFirstName;
    private String authorLastName;
    private MessageType messageType;
    private String content;
    private LocalDateTime timestamp;
    private Integer conversationId;

}
