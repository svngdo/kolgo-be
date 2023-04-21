package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.EmailRequest;
import com.dtu.kolgo.dto.request.LoginRequest;
import com.dtu.kolgo.dto.request.RegisterRequest;
import com.dtu.kolgo.dto.request.PasswordResetRequest;
import com.dtu.kolgo.dto.response.TokenResponse;
import com.dtu.kolgo.dto.response.UserResponse;
import com.dtu.kolgo.dto.response.ApiResponse;

public interface AuthenticationService {

    ApiResponse register(RegisterRequest request);

    ApiResponse verify(String token, boolean isBiz);

    UserResponse login(LoginRequest request);

    void authenticate(int id, String password);

    TokenResponse refreshToken(String refreshToken);

    ApiResponse forgotPassword(EmailRequest request);

    ApiResponse resetPassword(String resetPasswordToken, PasswordResetRequest request);

}
