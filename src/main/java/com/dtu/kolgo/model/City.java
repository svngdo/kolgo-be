package com.dtu.kolgo.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "cities")
public class City extends BaseShort {

    @Column
    private String name;
    @Column
    private String code;

}
