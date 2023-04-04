package com.dtu.kolgo.dto.response;

import com.dtu.kolgo.model.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "token",
        "id",
        "firstName",
        "lastName",
        "email",
        "roles"
})
public class UserResponse {

    private RefreshTokenResponse token;
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Role> roles;

}
