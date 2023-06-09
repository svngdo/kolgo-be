package com.dtu.kolgo.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PasswordResetDto {

    @NotBlank
    private String newPassword;
    @NotBlank
    private String confirmPassword;

}
