package com.dtu.kolgo.controller;

import com.dtu.kolgo.dto.booking.BookingCreateDto;
import com.dtu.kolgo.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class BookingController {

    private final BookingService service;

    @PostMapping("bookings")
    public ResponseEntity<?> post(
            Principal principal,
            @RequestBody @Valid BookingCreateDto dto
    ) {
        return new ResponseEntity<>(
                service.create(principal, dto),
                HttpStatus.OK
        );
    }

    @GetMapping("bookings/{id}")
    public ResponseEntity<?> get(
            @PathVariable("id") int id
    ) {
        return new ResponseEntity<>(
                service.getDtoById(id),
                HttpStatus.OK
        );
    }

    @GetMapping("bookings/user/{userId}")
    public ResponseEntity<?> getAll(
            @PathVariable("userId") int userId
    ) {
        return new ResponseEntity<>(
                service.getAllDtoByUserId(userId),
                HttpStatus.OK
        );
    }

    @GetMapping("bookings/user")
    public ResponseEntity<?> getAll(
            Principal principal
    ) {
        return new ResponseEntity<>(
                service.getAllDtoByPrincipal(principal),
                HttpStatus.OK
        );
    }

}
