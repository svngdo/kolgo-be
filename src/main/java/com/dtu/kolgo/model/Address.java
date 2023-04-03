package com.dtu.kolgo.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "addresses")
public class Address extends Base {

    @Column
    private String buildingNumber;
    @Column
    private String streetName;
    @Column
    private String ward;
    @Column
    private String district;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;

}
