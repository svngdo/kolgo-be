package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.ForgotPasswordRequest;
import com.dtu.kolgo.dto.request.AuthLoginRequest;
import com.dtu.kolgo.dto.request.RegisterRequest;
import com.dtu.kolgo.dto.request.ResetPasswordRequest;
import com.dtu.kolgo.dto.response.TokenResponse;
import com.dtu.kolgo.dto.response.UserResponse;
import com.dtu.kolgo.dto.response.WebResponse;

public interface AuthenticationService {

    WebResponse register(RegisterRequest request);

    WebResponse verify(String token, boolean isBiz);

    UserResponse login(AuthLoginRequest request);

    void authenticate(int id, String password);

    TokenResponse refreshToken(String refreshToken);

    WebResponse forgotPassword(ForgotPasswordRequest request);

    WebResponse resetPassword(String resetPasswordToken, ResetPasswordRequest request);

}
