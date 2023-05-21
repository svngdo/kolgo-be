package com.dtu.kolgo.model;

import com.dtu.kolgo.enums.ChatMessageType;
import com.dtu.kolgo.util.DateTimeUtils;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "chat_messages")
public class ChatMessage extends BaseInt {

    @Enumerated(EnumType.STRING)
    private ChatMessageType type;
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
    @Column(name = "content")
    private String content;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chat_id")
    private Chat chat;

    public String getTimestamp() {
        return DateTimeUtils.convertToString(timestamp);
    }
}
