package com.dtu.kolgo.dto.request;

import lombok.Data;

@Data
public class KolUpdateRequest {

    private String firstName;
    private String lastName;
    private String avatar;
    private String gender;
    private String phoneNumber;
    private String city;
    private String speciality;
    private String facebookUrl;
    private String instagramUrl;
    private String tiktokUrl;
    private String youtubeUrl;

}
