package com.dtu.kolgo.controller;

import com.dtu.kolgo.dto.enterprise.EnterpriseProfileDto;
import com.dtu.kolgo.service.EnterpriseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @PathVariable("id") int id
    ) {
        return new ResponseEntity<>(
                service.getDetailsById(id),
                HttpStatus.OK
        );
    }

    @GetMapping("ents/fields/{fieldId}")
    public ResponseEntity<?> getAllByField(
            @PathVariable("fieldId") short fieldId
    ) {
        return new ResponseEntity<>(
                service.getAllDtoByFieldId(fieldId),
                HttpStatus.OK
        );
    }

    @DeleteMapping("ents/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") int entId
    ) {
        return new ResponseEntity<>(
                service.deleteById(entId),
                HttpStatus.OK
        );
    }

    @GetMapping("ent/profile")
    public ResponseEntity<?> getProfile(
            Principal principal
    ) {
        return new ResponseEntity<>(
                service.getProfileByPrincipal(principal),
                HttpStatus.OK
        );
    }

    @PutMapping("ent/profile")
    public ResponseEntity<?> putProfile(
            Principal principal,
            @RequestBody EnterpriseProfileDto profile
    ) {
        return new ResponseEntity<>(
                service.updateProfileByPrincipal(principal, profile),
                HttpStatus.OK
        );
    }

}