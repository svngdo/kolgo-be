package com.dtu.kolgo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserEmailRequest {

    @NotNull
    @NotBlank
    @Email
    private String email;

}
