package com.dtu.kolgo.dto.request;

import lombok.Data;

@Data
public class EnterpriseUpdateRequest {

    private int id;
    private String firstName;
    private String lastName;
    private String avatar;
    private String email;
    private String name;
    private String phoneNumber;
    private String taxIdentificationNumber;
    private String buildingNumber;
    private String streetName;
    private String ward;
    private String district;
    private String city;

}
