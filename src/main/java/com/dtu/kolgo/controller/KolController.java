package com.dtu.kolgo.controller;

import com.dtu.kolgo.dto.request.KolUpdateRequest;
import com.dtu.kolgo.service.KolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class KolController {

    private final KolService service;

    @GetMapping("kols")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(
                service.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("kols/{id}")
    public ResponseEntity<?> get(
            @PathVariable("id") int kolId
    ) {
        return new ResponseEntity<>(
                service.getProfileById(kolId),
                HttpStatus.OK
        );
    }

    @GetMapping("kols/fields/{fieldId}")
    public ResponseEntity<?> getAllByField(
            @PathVariable("fieldId") short fieldId
    ) {
        return new ResponseEntity<>(
                service.getAllByFieldId(fieldId),
                HttpStatus.OK
        );
    }

    @PutMapping("kols/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") int kolId,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar,
            @RequestParam(value = "images", required = false) List<MultipartFile> images,
            @RequestBody KolUpdateRequest request
    ) {
        return new ResponseEntity<>(
                service.updateProfileById(kolId, request, avatar, images),
                HttpStatus.OK
        );
    }

    @DeleteMapping("kols/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") int userId
    ) {
        return new ResponseEntity<>(
                service.delete(userId),
                HttpStatus.OK
        );
    }

    @GetMapping("kol/profile")
    public ResponseEntity<?> getProfile(
            Principal principal
    ) {
        return new ResponseEntity<>(
                service.getProfileByPrincipal(principal),
                HttpStatus.OK
        );
    }

    @PutMapping("kol/profile")
    public ResponseEntity<?> updateKolProfile(
            Principal principal,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar,
            @RequestParam(value = "images", required = false) List<MultipartFile> images,
            @ModelAttribute KolUpdateRequest request
    ) {
        return new ResponseEntity<>(
                service.updateProfileByPrincipal(principal, request, avatar, images),
                HttpStatus.OK
        );
    }
}
