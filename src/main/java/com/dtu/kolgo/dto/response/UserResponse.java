package com.dtu.kolgo.dto.response;

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
        "userId",
        "avatar",
        "firstName",
        "lastName",
        "email",
        "phoneNumber",
        "roles",
        "token"
})
public class UserResponse {

    private int userId;
    private String avatar;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private List<String> roles;
    private TokenResponse token;

}
