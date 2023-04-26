package com.dtu.kolgo.controller;

import com.dtu.kolgo.dto.request.EmailRequest;
import com.dtu.kolgo.dto.request.PasswordUpdateRequest;
import com.dtu.kolgo.dto.request.UserUpdateRequest;
import com.dtu.kolgo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("users")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(
                service.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("users/{id}")
    public ResponseEntity<?> get(
            @PathVariable("id") int userId
    ) {
        return new ResponseEntity<>(
                service.getResponseById(userId),
                HttpStatus.OK
        );
    }

    @PutMapping("users/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") int userId,
            @RequestBody UserUpdateRequest request
    ) {
        return new ResponseEntity<>(
                service.update(userId, request),
                HttpStatus.OK
        );
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") int userId
    ) {
        return new ResponseEntity<>(
                service.delete(userId),
                HttpStatus.OK
        );
    }

    @PutMapping("user/email")
    public ResponseEntity<?> updateUserEmail(
            Principal principal,
            @RequestBody EmailRequest request
    ) {
        return new ResponseEntity<>(
                service.updateEmail(principal, request),
                HttpStatus.OK
        );
    }

    @PutMapping("user/password")
    public ResponseEntity<?> updateUserPassword(
            Principal principal,
            @RequestBody PasswordUpdateRequest request
    ) {
        return new ResponseEntity<>(
                service.updatePassword(principal, request),
                HttpStatus.OK
        );
    }


}
