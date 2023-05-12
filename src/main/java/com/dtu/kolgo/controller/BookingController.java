package com.dtu.kolgo.controller;

import com.dtu.kolgo.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookingController {

    private final BookingService service;

    @GetMapping("bookings/{id}")
    public ResponseEntity<?> get(
            @PathVariable("id") int id
    ) {
        return new ResponseEntity<>(
                service.getDtoById(id),
                HttpStatus.OK
        );
    }

}
