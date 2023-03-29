package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.LoginRequest;
import com.dtu.kolgo.dto.request.RegisterRequest;
import com.dtu.kolgo.dto.request.ResetPasswordRequest;
import com.dtu.kolgo.dto.request.UpdatePasswordRequest;
import com.dtu.kolgo.dto.response.LoginResponse;
import com.dtu.kolgo.dto.response.RefreshTokenResponse;
import com.dtu.kolgo.dto.response.WebResponse;

public interface AuthenticationService {

    WebResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    void authenticate(int id, String password);

    RefreshTokenResponse refreshToken(String refreshToken);

    WebResponse resetPassword(ResetPasswordRequest request);

    WebResponse updatePassword(String resetPasswordToken, UpdatePasswordRequest request);

}
