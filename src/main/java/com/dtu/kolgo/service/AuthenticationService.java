package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.AuthDto;
import com.dtu.kolgo.dto.EmailDto;
import com.dtu.kolgo.dto.LoginDto;
import com.dtu.kolgo.dto.PasswordResetDto;
import com.dtu.kolgo.dto.request.RegisterRequest;
import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.dto.TokenDto;

public interface AuthenticationService {

    ApiResponse register(RegisterRequest request);

    ApiResponse verify(String token, boolean isBiz);

    AuthDto login(LoginDto request);

    void authenticate(int id, String password);

    TokenDto refreshToken(String refreshToken);

    ApiResponse forgotPassword(EmailDto request);

    ApiResponse resetPassword(String resetPasswordToken, PasswordResetDto request);

}
