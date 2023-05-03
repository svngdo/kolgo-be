package com.dtu.kolgo.controller;

import com.dtu.kolgo.dto.kol.KolProfileDto;
import com.dtu.kolgo.service.KolService;
import jakarta.validation.Valid;
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
    public ResponseEntity<?> get() {
        return new ResponseEntity<>(
                service.getAllDto(),
                HttpStatus.OK
        );
    }

    @GetMapping("kols/{id}")
    public ResponseEntity<?> get(
            @PathVariable("id") int id
    ) {
        return new ResponseEntity<>(
                service.getDetails(id),
                HttpStatus.OK
        );
    }

    @GetMapping("kols/fields/{fieldId}")
    public ResponseEntity<?> get(
            @PathVariable("fieldId") short fieldId
    ) {
        return new ResponseEntity<>(
                service.getAllDtoByField(fieldId),
                HttpStatus.OK
        );
    }

    @PutMapping("kols/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") int kolId,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar,
            @RequestParam(value = "images", required = false) List<MultipartFile> images,
            @ModelAttribute @Valid KolProfileDto dto
            ) {
        return new ResponseEntity<>(
                service.updateProfile(kolId, dto, avatar, images),
                HttpStatus.OK
        );
    }

    @DeleteMapping("kols/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") int id
    ) {
        return new ResponseEntity<>(
                service.delete(id),
                HttpStatus.OK
        );
    }

    @GetMapping("kol/profile")
    public ResponseEntity<?> getProfile(
            Principal principal
    ) {
        return new ResponseEntity<>(
                service.getDto(principal),
                HttpStatus.OK
        );
    }

    @PutMapping("kol/profile")
    public ResponseEntity<?> updateProfile(
            Principal principal,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar,
            @RequestParam(value = "images", required = false) List<MultipartFile> images,
            @ModelAttribute @Valid KolProfileDto dto
    ) {
        return new ResponseEntity<>(
                service.updateProfile(principal, dto, avatar, images),
                HttpStatus.OK
        );
    }

}
