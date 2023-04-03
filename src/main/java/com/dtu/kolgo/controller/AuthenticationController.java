package com.dtu.kolgo.controller;

import com.dtu.kolgo.dto.request.LoginRequest;
import com.dtu.kolgo.dto.request.RegisterRequest;
import com.dtu.kolgo.dto.request.ResetPasswordRequest;
import com.dtu.kolgo.dto.request.UpdatePasswordRequest;
import com.dtu.kolgo.service.AuthenticationService;
import com.dtu.kolgo.service.EnterpriseService;
import com.dtu.kolgo.service.KolService;
import com.dtu.kolgo.service.UserService;
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
    private final UserService userService;
    private final KolService kolService;
    private final EnterpriseService enterpriseService;

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

    @GetMapping("refresh_token")
    public ResponseEntity<?> token(
            @RequestParam(value = "token") String token
    ) {
        return new ResponseEntity<>(
                service.refreshToken(token),
                HttpStatus.OK);
    }

    @PostMapping("reset_password")
    public ResponseEntity<?> resetPassword(
            @RequestBody @Valid ResetPasswordRequest request
    ) {
        return new ResponseEntity<>(
                userService.resetPassword(request),
                HttpStatus.OK);
    }

    @PostMapping("update_password")
    public ResponseEntity<?> updatePassword(
            @RequestParam("reset_password_token") String token,
            @RequestBody @Valid UpdatePasswordRequest request
    ) {
        return new ResponseEntity<>(
                userService.updatePassword(token, request),
                HttpStatus.OK
        );
    }

}
