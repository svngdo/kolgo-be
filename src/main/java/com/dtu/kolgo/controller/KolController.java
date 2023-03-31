package com.dtu.kolgo.controller;

import com.dtu.kolgo.dto.request.KolRegisterRequest;
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

    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody KolRegisterRequest request
    ) {
        return new ResponseEntity<>(
                service.save(request),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<?> fetchAll() {
        return new ResponseEntity<>(
                service.fetchAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<?> fetch(
            @PathVariable("id") int id
    ) {
        return new ResponseEntity<>(
                service.fetch(id),
                HttpStatus.OK
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") int id,
            @RequestBody KolUpdateRequest request
    ) {
        return new ResponseEntity<>(
                service.update(id, request),
                HttpStatus.OK
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") int id
    ) {
        return new ResponseEntity<>(
                service.delete(id),
                HttpStatus.OK
        );
    }

}
