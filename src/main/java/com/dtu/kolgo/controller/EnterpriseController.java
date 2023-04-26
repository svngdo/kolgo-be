package com.dtu.kolgo.controller;

import com.dtu.kolgo.dto.request.EntUpdateRequest;
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
                service.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("ents/{id}")
    public ResponseEntity<?> get(
            @PathVariable("id") int entId
    ) {
        return new ResponseEntity<>(
                service.getProfileById(entId),
                HttpStatus.OK
        );
    }

    @PutMapping("ents/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") int entId,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar,
            @RequestBody EntUpdateRequest request
    ) {
        return new ResponseEntity<>(
                service.updateProfileById(entId, request, avatar),
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
                service.getProfileByPrincipal(principal),
                HttpStatus.OK
        );
    }

    @PutMapping("ent/profile")
    public ResponseEntity<?> updateEnterpriseProfile(
            Principal principal,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar,
            @ModelAttribute EntUpdateRequest request
    ) {
        log.info(request.toString());
        return new ResponseEntity<>(
                service.updateProfileByPrincipal(principal, request, avatar),
                HttpStatus.OK
        );
    }


}