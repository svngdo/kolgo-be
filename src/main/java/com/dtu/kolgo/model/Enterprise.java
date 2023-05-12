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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "name")
    private String name;
    @Column(name = "tax_id")
    private String taxId;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;
    @Column(name = "phone")
    private String phone;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "enterprise_fields",
            joinColumns = @JoinColumn(name = "enterprise_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "field_id", referencedColumnName = "id"))
    private List<Field> fields;
    @OneToMany(mappedBy = "enterprise", cascade = CascadeType.ALL)
    private List<Campaign> campaigns;

}
