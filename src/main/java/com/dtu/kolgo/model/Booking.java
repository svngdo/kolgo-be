package com.dtu.kolgo.model;

import com.dtu.kolgo.enums.BookingStatus;
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
@Table(name = "bookings")
public class Booking extends BaseInt {

    @Column(name = "date")
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookingStatus status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "kol_id")
    private Kol kol;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_id")
    private Payment payment;
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<Feedback> feedbacks;

}
