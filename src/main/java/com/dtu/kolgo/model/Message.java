package com.dtu.kolgo.model;

import com.dtu.kolgo.enums.MessageType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "messages")
public class Message extends BaseInt {

    @Enumerated(EnumType.STRING)
    private MessageType type;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    private String content;
    @Column
    private LocalDateTime timestamp;
    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

}
