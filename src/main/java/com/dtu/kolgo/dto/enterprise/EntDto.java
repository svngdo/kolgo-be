package com.dtu.kolgo.dto.enterprise;

import com.dtu.kolgo.dto.user.UserDto;
import com.dtu.kolgo.model.Address;
import com.dtu.kolgo.model.Field;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EntDto {

    private UserDto user;
    private Integer id;
    private String name;
    private String phone;
    private String taxId;
    private Address address;
    private Field field;

}
