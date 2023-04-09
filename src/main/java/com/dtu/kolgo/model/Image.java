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

    @Column(nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "kol_id")
    private Kol kol;

}
