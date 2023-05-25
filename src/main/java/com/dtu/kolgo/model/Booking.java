package com.dtu.kolgo.model;

import com.dtu.kolgo.enums.BookingStatus;
import com.dtu.kolgo.util.DateTimeUtils;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "bookings")
public class Booking extends BaseInt {

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
    @Column(name = "description")
    private String description;
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookingStatus status;
    @Column(name = "txn_ref")
    private String txnRef;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kol_id")
    private Kol kol;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_id")
    private Payment payment;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "feedback_id")
    private Feedback feedback;

    public String getTimestamp() {
        return DateTimeUtils.convertToString(timestamp);
    }

}
