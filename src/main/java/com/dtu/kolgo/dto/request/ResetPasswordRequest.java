package com.dtu.kolgo.dto.request;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class ResetPasswordRequest {

    @Email
    private String email;

}
