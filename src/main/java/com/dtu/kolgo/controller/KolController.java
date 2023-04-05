package com.dtu.kolgo.controller;

import com.dtu.kolgo.dto.request.KolUpdateRequest;
import com.dtu.kolgo.service.KolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kol")
@RequiredArgsConstructor
public class KolController {

    private final KolService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(
                service.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<?> get(
            @PathVariable("id") long userId
    ) {
        return new ResponseEntity<>(
                service.getByUserID(userId),
                HttpStatus.OK
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") int userId,
            @RequestBody KolUpdateRequest request
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

}
