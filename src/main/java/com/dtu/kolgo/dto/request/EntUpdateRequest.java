package com.dtu.kolgo.dto.request;

import lombok.Data;

@Data
public class EntUpdateRequest {

    private String firstName;
    private String lastName;
    private String name;
    private Short enterpriseFieldId;
    private String email;
    private String phoneNumber;
    private String taxIdentificationNumber;
    private String address;
    private Short cityId;

}
