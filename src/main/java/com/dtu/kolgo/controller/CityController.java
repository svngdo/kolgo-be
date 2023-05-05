package com.dtu.kolgo.controller;

import com.dtu.kolgo.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService service;

    @GetMapping
    public ResponseEntity<?> get() {
        return new ResponseEntity<>(
                service.getAll(),
                HttpStatus.OK
        );
    }

}
