package com.dtu.kolgo.controller;

import com.dtu.kolgo.dto.request.ChangePasswordRequest;
import com.dtu.kolgo.dto.request.ResetPasswordRequest;
import com.dtu.kolgo.dto.request.UpdatePasswordRequest;
import com.dtu.kolgo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("reset_password")
    public ResponseEntity<?> resetPassword(
            @RequestBody @Valid ResetPasswordRequest request
    ) {
        return new ResponseEntity<>(
                service.resetPassword(request),
                HttpStatus.OK);
    }

    @PostMapping("update_password")
    public ResponseEntity<?> updatePassword(
            @RequestParam("reset_password_token") String token,
            @RequestBody @Valid UpdatePasswordRequest request
    ) {
        return new ResponseEntity<>(
                service.updatePassword(token, request),
                HttpStatus.OK
        );
    }

    @PostMapping("change_password")
    public ResponseEntity<?> changePassword(
            Principal principal,
            @RequestBody ChangePasswordRequest request
    ) {
        return new ResponseEntity<>(
                service.changePassword(principal, request),
                HttpStatus.OK
        );
    }

}
