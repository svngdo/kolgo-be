package com.dtu.kolgo.model;


import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "payments")
public class Payment extends Base {

    @Column
    private String amountPaid;
    @Column
    private String referenceNumber;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kol_id")
    private Kol kol;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;

}
