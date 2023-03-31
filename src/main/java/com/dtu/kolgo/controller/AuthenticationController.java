package com.dtu.kolgo.controller;

import com.dtu.kolgo.dto.request.LoginRequest;
import com.dtu.kolgo.dto.request.RegisterRequest;
import com.dtu.kolgo.dto.request.ResetPasswordRequest;
import com.dtu.kolgo.dto.request.UpdatePasswordRequest;
import com.dtu.kolgo.service.impl.AuthenticationServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationServiceImpl service;

    @PostMapping("register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ) {
        return new ResponseEntity<>(
                service.register(request),
                HttpStatus.OK
        );
    }

    @PostMapping("login")
    public ResponseEntity<?> authenticate(@RequestBody @Valid LoginRequest request) {
        return new ResponseEntity<>(
                service.login(request),
                HttpStatus.OK);
    }

    @GetMapping("refresh-token")
    public ResponseEntity<?> token(
            @RequestParam(value = "token") String refreshToken
    ) {
        return new ResponseEntity<>(
                service.refreshToken(refreshToken),
                HttpStatus.OK);
    }

    @PostMapping("reset-password")
    public ResponseEntity<?> resetPassword(
            @RequestBody @Valid ResetPasswordRequest request
    ) {
        return new ResponseEntity<>(
                service.resetPassword(request),
                HttpStatus.OK);
    }

    @PostMapping("update-password")
    public ResponseEntity<?> updatePassword(
            @RequestParam("reset_password_token") String resetPasswordToken,
            @RequestBody @Valid UpdatePasswordRequest request
    ) {
        return new ResponseEntity<>(
                service.updatePassword(resetPasswordToken, request),
                HttpStatus.OK
        );
    }

}
