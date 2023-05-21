package com.dtu.kolgo.model;

import com.dtu.kolgo.enums.ChatType;
import com.dtu.kolgo.util.DateTimeUtils;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "chats")
public class Chat extends BaseInt {

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ChatType type;
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "chat_users",
            joinColumns = @JoinColumn(name = "chat_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> users;
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ChatMessage> chatMessages;

    public String getTimestamp() {
        return DateTimeUtils.convertToString(timestamp);
    }

    public List<Integer> getUserIds() {
        return users.stream().map(BaseInt::getId).toList();
    }

}
