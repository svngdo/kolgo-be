package com.dtu.kolgo.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "feedbacks")
public class Feedback extends BaseInt {

    @Column
    private Short rating;
    @Column
    private String comment;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

}
