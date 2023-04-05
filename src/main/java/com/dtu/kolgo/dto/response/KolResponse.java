package com.dtu.kolgo.dto.response;

import com.dtu.kolgo.model.Role;
import com.dtu.kolgo.util.constant.Gender;
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
        "gender",
        "phoneNumber",
        "city",
        "speciality",
        "facebookUrl",
        "instagramUrl",
        "tiktokUrl",
        "youtubeUrl",
        "roles"
})
public class KolResponse {

    private TokenResponse token;
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Gender gender;
    private String city;
    private String speciality;
    private String facebookUrl;
    private String instagramUrl;
    private String tiktokUrl;
    private String youtubeUrl;
    private List<Role> roles;

}
