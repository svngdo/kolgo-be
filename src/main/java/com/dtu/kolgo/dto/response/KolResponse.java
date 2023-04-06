package com.dtu.kolgo.dto.response;

import com.dtu.kolgo.model.Feedback;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "token",
        "id",
        "firstName",
        "lastName",
        "avatar",
        "gender",
        "email",
        "phoneNumber",
        "city",
        "speciality",
        "facebookUrl",
        "instagramUrl",
        "tiktokUrl",
        "youtubeUrl",
        "feedback"
})
public class KolResponse {

    private TokenResponse token;
    private int id;
    private String firstName;
    private String lastName;
    private String avatar;
    private String gender;
    private String email;
    private String phoneNumber;
    private String city;
    private String speciality;
    private String facebookUrl;
    private String instagramUrl;
    private String tiktokUrl;
    private String youtubeUrl;
    private List<Feedback> feedbacks;

}
