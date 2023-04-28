package com.dtu.kolgo.model;


import com.dtu.kolgo.enums.PaymentMethod;
import com.dtu.kolgo.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "payments")
public class Payment extends BaseInt {

    @Enumerated(EnumType.STRING)
    @Column(name = "method", nullable = false)
    private PaymentMethod method;
    @Column(name = "amount_paid", nullable = false)
    private BigDecimal amountPaid;
    @Column(name = "description")
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PaymentStatus status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
