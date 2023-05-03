package com.dtu.kolgo.controller;

import com.dtu.kolgo.dto.enterprise.EntProfileDto;
import com.dtu.kolgo.service.EnterpriseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EnterpriseController {

    private final EnterpriseService service;

    @GetMapping("ents")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(
                service.getAllDto(),
                HttpStatus.OK
        );
    }

    @GetMapping("ents/{id}")
    public ResponseEntity<?> get(
            @PathVariable("id") int entId
    ) {
        return new ResponseEntity<>(
                service.getDetails(entId),
                HttpStatus.OK
        );
    }

    @GetMapping("ents/fields/{fieldId}")
    public ResponseEntity<?> getAllByField(
            @PathVariable("fieldId") short fieldId
    ) {
        return new ResponseEntity<>(
                service.getAllDtoByField(fieldId),
                HttpStatus.OK
        );
    }

    @PutMapping("ents/{id}")
    public ResponseEntity<?> put(
            @PathVariable("id") int entId,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar,
            @RequestBody EntProfileDto dto
    ) {
        return new ResponseEntity<>(
                service.update(entId, dto, avatar),
                HttpStatus.OK
        );
    }

    @DeleteMapping("ents/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") int entId
    ) {
        return new ResponseEntity<>(
                service.delete(entId),
                HttpStatus.OK
        );
    }

    @GetMapping("ent/profile")
    public ResponseEntity<?> getEnterpriseProfile(
            Principal principal
    ) {
        return new ResponseEntity<>(
                service.getDto(principal),
                HttpStatus.OK
        );
    }

    @PutMapping("ent/profile")
    public ResponseEntity<?> updateEnterpriseProfile(
            Principal principal,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar,
            @ModelAttribute EntProfileDto dto
    ) {
        return new ResponseEntity<>(
                service.update(principal, dto, avatar),
                HttpStatus.OK
        );
    }

}