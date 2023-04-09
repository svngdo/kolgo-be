package com.dtu.kolgo.controller;

import com.dtu.kolgo.dto.request.*;
import com.dtu.kolgo.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("register")
    public ResponseEntity<?> register(
            @RequestBody @Valid RegisterRequest request
    ) {
        return new ResponseEntity<>(
                service.register(request),
                HttpStatus.OK
        );
    }

    @GetMapping("verify")
    public ResponseEntity<?> verify(
            @RequestParam("biz") boolean isBiz,
            @RequestParam("verify_account_token") String token
    ) {
        System.out.println("BIZ " + isBiz);
        return new ResponseEntity<>(
                service.verify(token, isBiz),
                HttpStatus.OK
        );
    }

    @PostMapping("login")
    public ResponseEntity<?> authenticate(
            @RequestBody @Valid LoginRequest request
    ) {
        return new ResponseEntity<>(
                service.login(request),
                HttpStatus.OK);
    }

    @PostMapping("forgot_password")
    public ResponseEntity<?> forgotPassword(
            @RequestBody @Valid EmailRequest request
    ) {
        return new ResponseEntity<>(
                service.forgotPassword(request),
                HttpStatus.OK);
    }

    @PostMapping("reset_password")
    public ResponseEntity<?> resetPassword(
            @RequestParam("reset_password_token") String token,
            @RequestBody @Valid ResetPasswordRequest request
    ) {
        return new ResponseEntity<>(
                service.resetPassword(token, request),
                HttpStatus.OK
        );
    }

    @GetMapping("refresh_token")
    public ResponseEntity<?> token(
            @RequestParam(value = "token") String token
    ) {
        return new ResponseEntity<>(
                service.refreshToken(token),
                HttpStatus.OK);
    }

}
