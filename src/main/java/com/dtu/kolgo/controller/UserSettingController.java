package com.dtu.kolgo.controller;

import com.dtu.kolgo.service.UserSettingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("settings")
@RequiredArgsConstructor
@Slf4j
public class UserSettingController {

    private final UserSettingService service;


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
