package com.dtu.kolgo.controller;

import com.dtu.kolgo.enums.NotificationStatus;
import com.dtu.kolgo.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;

    @GetMapping("notification")
    public ResponseEntity<?> getNotification(
            Principal principal
    ) {
        return new ResponseEntity<>(
                service.getAllByPrincipal(principal),
                HttpStatus.OK
        );
    }

    @PutMapping("notification/{id}")
    public ResponseEntity<?> updateStatus(
            Principal principal,
            @PathVariable("id") int id,
            @RequestParam("status") NotificationStatus status
    ) {
        return new ResponseEntity<>(
                service.updateStatus(principal, id, status),
                HttpStatus.OK
        );
    }

}
