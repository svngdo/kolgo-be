package com.dtu.kolgo.model;

import com.dtu.kolgo.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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
    @Column(name = "post_price")
    private BigDecimal postPrice;
    @Column(name = "post_number")
    private Integer postNumber;
    @Column(name = "video_price")
    private BigDecimal videoPrice;
    @Column(name = "video_number")
    private Integer videoNumber;
    @Column(name = "total_price")
    private BigDecimal totalPrice;
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
