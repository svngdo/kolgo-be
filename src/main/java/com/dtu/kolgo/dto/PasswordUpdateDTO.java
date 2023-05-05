package com.dtu.kolgo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PasswordUpdateDTO {

    @NotBlank
    private String oldPassword;
    @NotBlank
    private String newPassword;

}
