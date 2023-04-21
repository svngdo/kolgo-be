package com.dtu.kolgo.dto.request;

import lombok.Data;

@Data
public class UpdateEnterpriseRequest {

    private String firstName;
    private String lastName;
    private String name;
    private Short enterpriseFieldId;
    private String email;
    private String phoneNumber;
    private String taxIdentificationNumber;
    private String buildingNumber;
    private String streetName;
    private String ward;
    private String district;
    private Short cityId;

}
