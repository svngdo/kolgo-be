package com.dtu.kolgo.dto.request;

import lombok.Data;

@Data
public class KolUpdateRequest {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String gender;
    private String city;
    private String speciality;
    private String location;
    private String facebookUrl;
    private String instagramUrl;
    private String tiktokUrl;
    private String youtubeUrl;

}
