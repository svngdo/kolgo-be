package com.dtu.kolgo.controller;

import com.dtu.kolgo.service.EnterpriseFieldService;
import com.dtu.kolgo.service.KolFieldService;
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

    private final KolFieldService kolFieldService;
    private final EnterpriseFieldService entFieldService;

    @GetMapping("kol")
    public ResponseEntity<?> getKolFields() {
        return new ResponseEntity<>(
                kolFieldService.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("ent")
    public ResponseEntity<?> getEntFields() {
        return new ResponseEntity<>(
                entFieldService.getAll(),
                HttpStatus.OK
        );
    }

}
