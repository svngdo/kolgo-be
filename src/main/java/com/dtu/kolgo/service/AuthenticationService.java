package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.AuthForgotPasswordRequest;
import com.dtu.kolgo.dto.request.AuthLoginRequest;
import com.dtu.kolgo.dto.request.AuthRegisterRequest;
import com.dtu.kolgo.dto.request.AuthResetPasswordRequest;
import com.dtu.kolgo.dto.response.TokenResponse;
import com.dtu.kolgo.dto.response.UserResponse;
import com.dtu.kolgo.dto.response.WebResponse;

public interface AuthenticationService {

    WebResponse register(AuthRegisterRequest request);

    WebResponse verify(String token, boolean isBiz);

    UserResponse login(AuthLoginRequest request);

    void authenticate(int id, String password);

    TokenResponse refreshToken(String refreshToken);

    WebResponse forgotPassword(AuthForgotPasswordRequest request);

    WebResponse resetPassword(String resetPasswordToken, AuthResetPasswordRequest request);

}
