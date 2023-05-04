package com.dtu.kolgo.dto.enterprise;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EntProfileDto {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String name;
    private String phone;
    private String taxId;
    @NotNull
    private Short cityId;
    private String addressDetails;
    @NotNull
    private Short fieldId;

}
