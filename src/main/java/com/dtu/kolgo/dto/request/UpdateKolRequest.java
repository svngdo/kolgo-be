package com.dtu.kolgo.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
public class UpdateKolRequest {

    private MultipartFile avatar;
    private Set<MultipartFile> images;
    private String firstName;
    private String lastName;
    private short genderId;
    private String phoneNumber;
    private short cityId;
    private String speciality;
    private String facebookUrl;
    private String instagramUrl;
    private String tiktokUrl;
    private String youtubeUrl;

}
