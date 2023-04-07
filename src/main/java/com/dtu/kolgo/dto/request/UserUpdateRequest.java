package com.dtu.kolgo.dto.request;

import com.dtu.kolgo.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserUpdateRequest {

    private String firstName;
    private String lastName;
    private String avatar;
    private String email;
    private List<Role> roles;

}
