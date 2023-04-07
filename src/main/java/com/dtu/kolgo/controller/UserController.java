package com.dtu.kolgo.controller;

import com.dtu.kolgo.dto.request.ChangePasswordRequest;
import com.dtu.kolgo.dto.request.UserUpdateRequest;
import com.dtu.kolgo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(
                service.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<?> get(
            @PathVariable("id") int userId
    ) {
        return new ResponseEntity<>(
                service.getResponseById(userId),
                HttpStatus.OK
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") int userId,
            @RequestBody UserUpdateRequest request
    ) {
        return new ResponseEntity<>(
                service.update(userId, request),
                HttpStatus.OK
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") int userId
    ) {
        return new ResponseEntity<>(
                service.delete(userId),
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
