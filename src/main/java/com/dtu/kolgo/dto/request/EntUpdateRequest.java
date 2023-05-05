package com.dtu.kolgo.dto.request;

import com.dtu.kolgo.dto.UserDto;
import lombok.Data;

@Data
public class EntUpdateRequest {

    private String name;
    private String address;
    private Short cityId;
    private Short fieldId;
    private String taxId;
    private UserDto user;

}
