package com.dtu.kolgo.controller;

import com.dtu.kolgo.dto.request.EmailRequest;
import com.dtu.kolgo.dto.request.UpdateEnterpriseRequest;
import com.dtu.kolgo.dto.request.UpdateKolRequest;
import com.dtu.kolgo.dto.request.UpdatePasswordRequest;
import com.dtu.kolgo.service.UserSettingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("settings")
@RequiredArgsConstructor
@Slf4j
public class UserSettingController {

    private final UserSettingService service;

    @PutMapping("email")
    public ResponseEntity<?> updateUserEmail(
            Principal principal,
            @RequestBody EmailRequest request
    ) {
        return new ResponseEntity<>(
                service.updateUserEmail(principal, request),
                HttpStatus.OK
        );
    }

    @PutMapping("password")
    public ResponseEntity<?> updateUserPassword(
            Principal principal,
            @RequestBody UpdatePasswordRequest request
    ) {
        return new ResponseEntity<>(
                service.updateUserPassword(principal, request),
                HttpStatus.OK
        );
    }

    @GetMapping("kol-profile")
    public ResponseEntity<?> getKolProfile(
            Principal principal
    ) {
        return new ResponseEntity<>(
                service.getKolProfile(principal),
                HttpStatus.OK
        );
    }

    @PutMapping("kol-profile")
    public ResponseEntity<?> updateKolProfile(
            Principal principal,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar,
            @RequestParam(value = "images", required = false) List<MultipartFile> images,
            @ModelAttribute UpdateKolRequest request
    ) {
        log.info(request.toString());
        return new ResponseEntity<>(
                service.updateKolProfile(principal, request, avatar, images),
                HttpStatus.OK
        );
    }

    @GetMapping("ent-profile")
    public ResponseEntity<?> getEnterpriseProfile(
            Principal principal
    ) {
        return new ResponseEntity<>(
                service.getEnterpriseProfile(principal),
                HttpStatus.OK
        );
    }

    @PutMapping("ent-profile")
    public ResponseEntity<?> updateEnterpriseProfile(
            Principal principal,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar,
            @ModelAttribute UpdateEnterpriseRequest request
    ) {
        return new ResponseEntity<>(
                service.updateEnterpriseProfile(principal, request, avatar),
                HttpStatus.OK
        );
    }

    @GetMapping("booking-history")
    public ResponseEntity<?> getBookingHistory(
            Principal principal
    ) {
        return new ResponseEntity<>(
                service.getBookingHistory(principal),
                HttpStatus.OK
        );
    }

    @GetMapping("payment-history")
    public ResponseEntity<?> getPaymentHistory(
            Principal principal
    ) {
        return new ResponseEntity<>(
                service.getPaymentHistory(principal),
                HttpStatus.OK
        );
    }

}
