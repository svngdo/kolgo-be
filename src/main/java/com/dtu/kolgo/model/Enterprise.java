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
public class Enterprise extends BaseInt {

    @Column
    private String name;
    @OneToOne
    @JoinColumn(name = "enterprise_field_id")
    private EnterpriseField field;
    @Column
    private String taxIdentificationNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToMany(mappedBy = "enterprise", cascade = CascadeType.ALL)
    private List<Booking> bookings;
    @OneToMany(mappedBy = "enterprise", cascade = CascadeType.ALL)
    private List<Campaign> campaigns;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

}
