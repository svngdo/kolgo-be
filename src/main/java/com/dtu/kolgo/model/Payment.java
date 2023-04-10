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
public class Payment extends BaseInt {

    @Column(nullable = false)
    private int amount;
    @Column(nullable = false, unique = true)
    private String reference;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kol_id")
    private Kol kol;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;

}
