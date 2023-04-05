package com.dtu.kolgo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "enterprises")
public class Enterprise extends Base {

    @Column
    private String name;
    @Column(columnDefinition = "varchar(20)")
    private String phoneNumber;
    @Column
    private String taxIdentificationNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToMany(mappedBy = "enterprise", cascade = CascadeType.ALL)
    private List<Booking> bookings;
    @OneToMany(mappedBy = "enterprise", cascade = CascadeType.ALL)
    private List<Payment> payments;
    @OneToMany(mappedBy = "enterprise", cascade = CascadeType.ALL)
    private List<Campaign> campaigns;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

}
