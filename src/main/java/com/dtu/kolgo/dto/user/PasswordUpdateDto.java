package com.dtu.kolgo.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PasswordUpdateDto {

    @NotBlank
    private String oldPassword;
    @NotBlank
    private String newPassword;

}
