package com.dtu.kolgo.model;

import com.dtu.kolgo.enums.NotificationStatus;
import com.dtu.kolgo.enums.NotificationType;
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
@Table(name = "notifications")
public class Notification extends BaseInt {

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private NotificationType type;
    @Column(name = "content")
    private String content;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private NotificationStatus status;
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
    @Column(name = "booking_id")
    private Integer bookingId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public String getTimestamp() {
        return DateTimeUtils.convertToString(timestamp);
    }

}
