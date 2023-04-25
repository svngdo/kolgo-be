package com.dtu.kolgo.model;

import com.dtu.kolgo.enums.ConversationType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "conversations")
public class Conversation extends BaseInt {

    @Enumerated(EnumType.STRING)
    private ConversationType type;
    @ManyToMany
    @JoinTable(name = "user_conversations",
    joinColumns = @JoinColumn(name = "conversation_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> users;
    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Message> messages;

}
