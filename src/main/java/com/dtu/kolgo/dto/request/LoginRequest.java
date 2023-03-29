package com.dtu.kolgo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {

    @NotNull
    @NotBlank
    private String userInput;
    @NotNull
    @NotBlank
    private String password;

}
