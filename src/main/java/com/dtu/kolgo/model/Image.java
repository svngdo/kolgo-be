package com.dtu.kolgo.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "images")
public class Image extends BaseInt {

    @Column(name = "name")
    private String name;

}
