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
    private int rating;
    @Column
    private String comment;
    @ManyToOne
    @JoinColumn(name = "kol_id")
    private Kol kol;
    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;
    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

}
