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

    @GetMapping("bookings")
    public ResponseEntity<?> getAllResponses() {
        return new ResponseEntity<>(
                service.getAllResponses(),
                HttpStatus.OK
        );
    }

    @GetMapping("bookings/{id}")
    public ResponseEntity<?> getResponse(
            @PathVariable("id") int id
    ) {
        return new ResponseEntity<>(
                service.getResponse(id),
                HttpStatus.OK
        );
    }

    @GetMapping("bookings/user/{userId}")
    public ResponseEntity<?> getAll(
            @PathVariable("userId") int userId
    ) {
        return new ResponseEntity<>(
                service.getAllResponses(userId),
                HttpStatus.OK
        );
    }

}
