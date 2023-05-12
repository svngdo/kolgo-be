package com.dtu.kolgo.dto.enterprise;

import com.dtu.kolgo.enums.Role;
import com.dtu.kolgo.model.City;
import com.dtu.kolgo.model.Field;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EnterpriseDto {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String avatar;
    private Role role;
    private Integer id;
    private String name;
    private String phone;
    private String taxId;
    private City city;
    private String addressDetails;
    private List<Field> fields;

}
