package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.EmailRequest;
import com.dtu.kolgo.dto.request.LoginRequest;
import com.dtu.kolgo.dto.request.RegisterRequest;
import com.dtu.kolgo.dto.request.ResetPasswordRequest;
import com.dtu.kolgo.dto.response.TokenResponse;
import com.dtu.kolgo.dto.response.UserResponse;
import com.dtu.kolgo.dto.response.WebResponse;

public interface AuthenticationService {

    WebResponse register(RegisterRequest request);

    WebResponse verify(String token, boolean isBiz);

    UserResponse login(LoginRequest request);

    void authenticate(int id, String password);

    TokenResponse refreshToken(String refreshToken);

    WebResponse forgotPassword(EmailRequest request);

    WebResponse resetPassword(String resetPasswordToken, ResetPasswordRequest request);

}
