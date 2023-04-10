package com.dtu.kolgo.controller;

import com.dtu.kolgo.dto.request.UpdateEnterpriseRequest;
import com.dtu.kolgo.service.EnterpriseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ents")
@RequiredArgsConstructor
public class EnterpriseController {

    private final EnterpriseService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(
                service.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<?> get(
            @PathVariable("id") int entId
    ) {
        return new ResponseEntity<>(
                service.getProfileById(entId),
                HttpStatus.OK
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") int entId,
            @RequestBody UpdateEnterpriseRequest request
    ) {
        return new ResponseEntity<>(
                service.update(entId, request),
                HttpStatus.OK
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") int entId
    ) {
        return new ResponseEntity<>(
                service.delete(entId),
                HttpStatus.OK
        );
    }

}