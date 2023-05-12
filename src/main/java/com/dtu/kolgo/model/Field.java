package com.dtu.kolgo.model;

import com.dtu.kolgo.enums.FieldType;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "fields")
public class Field extends BaseShort {

    @Column(name = "name")
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private FieldType type;

}
