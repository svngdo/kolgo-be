package com.dtu.kolgo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotNull
    @Size(min = 3, max = 20)
    private String username;
    @NotNull
    @Size(min = 6, max = 36)
    private String password;
    @NotNull
    @NotBlank
    @Email
    private String email;

}