package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.user.AuthDto;
import com.dtu.kolgo.dto.user.EmailDto;
import com.dtu.kolgo.dto.user.LoginDto;
import com.dtu.kolgo.dto.user.PasswordResetDto;
import com.dtu.kolgo.dto.user.RegisterDto;
import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.dto.user.TokenDto;

public interface AuthenticationService {

    ApiResponse register(RegisterDto request);

    ApiResponse verify(String token, boolean isBiz);

    AuthDto login(LoginDto request);

    void authenticate(int id, String password);

    TokenDto refreshToken(String refreshToken);

    ApiResponse forgotPassword(EmailDto request);

    ApiResponse resetPassword(String resetPasswordToken, PasswordResetDto request);

}
