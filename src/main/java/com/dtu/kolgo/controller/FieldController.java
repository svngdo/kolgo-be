package com.dtu.kolgo.controller;

import com.dtu.kolgo.enums.FieldType;
import com.dtu.kolgo.service.FieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FieldController {

    private final FieldService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(
                service.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("fields/kol")
    public ResponseEntity<?> getAllTypeKol() {
        return new ResponseEntity<>(
                service.getAllByType(FieldType.KOL),
                HttpStatus.OK
        );
    }

    @GetMapping("fields/ent")
    public ResponseEntity<?> getAllTypeEnt() {
        return new ResponseEntity<>(
                service.getAllByType(FieldType.ENTERPRISE),
                HttpStatus.OK
        );
    }

}
