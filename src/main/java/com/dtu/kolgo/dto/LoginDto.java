package com.dtu.kolgo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;

}
