package com.dtu.kolgo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
        "id",
        "name",
        "gender",
        "phone_number",
        "speciality",
        "location",
        "social_media",
        "roles"
})
public class KolResponse {

    @JsonProperty("id")
    private int id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("speciality")
    private String speciality;
    @JsonProperty("location")
    private String location;
    @JsonProperty("facebook_url")
    private String facebookUrl;
    @JsonProperty("instagram_url")
    private String instagramUrl;
    @JsonProperty("tiktok_url")
    private String tiktokUrl;
    @JsonProperty("youtube_url")
    private String youtubeUrl;
    @JsonProperty("roles")
    private List<String> roles;

}
