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
                service.getAllResponses(),
                HttpStatus.OK
        );
    }

    @GetMapping("kols/{id}")
    public ResponseEntity<?> get(
            @PathVariable("id") int kolId
    ) {
        return new ResponseEntity<>(
                service.getProfile(kolId),
                HttpStatus.OK
        );
    }

    @GetMapping("kols/fields/{fieldId}")
    public ResponseEntity<?> getAllByField(
            @PathVariable("fieldId") short fieldId
    ) {
        return new ResponseEntity<>(
                service.getAllResponses(fieldId),
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
                service.updateProfile(kolId, request, avatar, images),
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
                service.getProfile(principal),
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
                service.updateProfile(principal, request, avatar, images),
                HttpStatus.OK
        );
    }

}
