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
public class Feedback extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int rating;
    @Column
    private String comment;
    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

}
