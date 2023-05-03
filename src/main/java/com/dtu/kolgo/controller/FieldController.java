package com.dtu.kolgo.controller;

import com.dtu.kolgo.enums.FieldType;
import com.dtu.kolgo.service.FieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fields")
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

    @GetMapping("kol")
    public ResponseEntity<?> getAllTypeKol() {
        return new ResponseEntity<>(
                service.getAll(FieldType.KOL),
                HttpStatus.OK
        );
    }

    @GetMapping("ent")
    public ResponseEntity<?> getAllTypeEnt() {
        return new ResponseEntity<>(
                service.getAll(FieldType.ENTERPRISE),
                HttpStatus.OK
        );
    }

}
