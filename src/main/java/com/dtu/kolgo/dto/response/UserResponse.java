package com.dtu.kolgo.dto.response;

import com.dtu.kolgo.enums.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "avatar",
        "firstName",
        "lastName",
        "email",
        "phoneNumber",
        "roles",
        "token"
})
public class UserResponse {

    private int id;
    private String avatar;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Role role;
    private TokenResponse token;

}
