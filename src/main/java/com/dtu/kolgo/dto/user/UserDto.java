package com.dtu.kolgo.dto.user;

import com.dtu.kolgo.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String avatar;
    private Role role;

}
