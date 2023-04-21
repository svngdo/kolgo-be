package com.dtu.kolgo.dto.request;

import lombok.Data;

@Data
public class UpdateKolRequest {

    private String firstName;
    private String lastName;
    private Short genderId;
    private String phoneNumber;
    private Short cityId;
    private Short kolFieldId;
    private String facebookUrl;
    private String instagramUrl;
    private String tiktokUrl;
    private String youtubeUrl;

}
