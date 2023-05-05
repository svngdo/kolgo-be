package com.dtu.kolgo.controller;

import com.dtu.kolgo.dto.EmailDto;
import com.dtu.kolgo.dto.PasswordUpdateDTO;
import com.dtu.kolgo.dto.UserDto;
import com.dtu.kolgo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("users")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(
                service.getAllDto(),
                HttpStatus.OK
        );
    }

    @GetMapping("users/{id}")
    public ResponseEntity<?> get(
            @PathVariable("id") int userId
    ) {
        return new ResponseEntity<>(
                service.getDtoById(userId),
                HttpStatus.OK
        );
    }

    @PutMapping("users/{id}")
    public ResponseEntity<?> put(
            @PathVariable("id") int id,
            @RequestBody @Valid UserDto dto
    ) {
        return new ResponseEntity<>(
                service.updateById(id, dto),
                HttpStatus.OK
        );
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") int userId
    ) {
        return new ResponseEntity<>(
                service.deleteById(userId),
                HttpStatus.OK
        );
    }

    @PutMapping("user/email")
    public ResponseEntity<?> putEmail(
            Principal principal,
            @RequestBody @Valid EmailDto dto
    ) {
        return new ResponseEntity<>(
                service.updateEmail(principal, dto),
                HttpStatus.OK
        );
    }

    @PutMapping("user/password")
    public ResponseEntity<?> putPassword(
            Principal principal,
            @RequestBody @Valid PasswordUpdateDTO request
    ) {
        return new ResponseEntity<>(
                service.updatePassword(principal, request),
                HttpStatus.OK
        );
    }

    @PutMapping("user/avatar")
    public ResponseEntity<?> putAvatar(
            Principal principal,
            @RequestParam("avatar") MultipartFile avatar
    ) {
        return new ResponseEntity<>(
                service.updateAvatar(principal, avatar),
                HttpStatus.OK
        );
    }

}
