package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.LoginRequest;
import com.dtu.kolgo.dto.request.RegisterRequest;
import com.dtu.kolgo.dto.response.LoginResponse;
import com.dtu.kolgo.dto.response.RefreshTokenResponse;
import com.dtu.kolgo.dto.response.WebResponse;

public interface AuthenticationService {

    WebResponse register(RegisterRequest request);

    WebResponse verify(String token, boolean isBiz);

    LoginResponse login(LoginRequest request);

    void authenticate(long id, String password);

    RefreshTokenResponse refreshToken(String refreshToken);

}
