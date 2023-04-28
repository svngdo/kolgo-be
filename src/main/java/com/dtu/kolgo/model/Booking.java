package com.dtu.kolgo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "bookings")
public class Booking extends BaseInt {

    @Column
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;
    @ManyToOne
    @JoinColumn(name = "kol_id")
    private Kol kol;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_id")
    private Payment payment;
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<Feedback> feedbacks;

}
