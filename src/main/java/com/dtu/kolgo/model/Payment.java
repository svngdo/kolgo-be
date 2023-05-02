package com.dtu.kolgo.model;


import com.dtu.kolgo.enums.BankCode;
import com.dtu.kolgo.enums.PaymentMethod;
import com.dtu.kolgo.enums.PaymentStatus;
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
@Table(name = "payments")
public class Payment extends BaseInt {

    @Enumerated(EnumType.STRING)
    @Column(name = "method", nullable = false)
    private PaymentMethod method;
    @Column(name = "txn_ref")
    private String txnRef;
    @Column(name = "txn_no")
    private String txnNo;
    @Column(name = "bank_txn_no")
    private String bankTxnNo;
    @Enumerated(EnumType.STRING)
    @Column(name = "bank_code")
    private BankCode bankCode;
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
    @Column(name = "description")
    private String description;
    @Column(name = "date")
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PaymentStatus status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
