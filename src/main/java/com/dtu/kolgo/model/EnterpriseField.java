package com.dtu.kolgo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "enterprise_fields")
public class EnterpriseField extends BaseShort {

    @Column
    private String name;

}
