package com.dtu.kolgo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "feedbacks")
public class Feedback extends BaseInt {

    @Column(name = "rating")
    private Short rating;
    @Column(name = "comment")
    private String comment;
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

}
