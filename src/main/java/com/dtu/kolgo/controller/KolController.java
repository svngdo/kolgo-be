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
    public ResponseEntity<?> getAll(
            @RequestParam(required = false) Short fieldId
    ) {
        System.out.println("FIELD ID " + fieldId);
        return new ResponseEntity<>(
                service.getAllDto(fieldId),
                HttpStatus.OK
        );
    }

    @GetMapping("kols/{id}")
    public ResponseEntity<?> get(
            @PathVariable("id") int id
    ) {
        return new ResponseEntity<>(
                service.getDetailsById(id),
                HttpStatus.OK
        );
    }

    @DeleteMapping("kols/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") int id
    ) {
        return new ResponseEntity<>(
                service.deleteById(id),
                HttpStatus.OK
        );
    }

    /*
        PROFILE
     */
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
    public ResponseEntity<?> updateProfile(
            Principal principal,
            @RequestBody @Valid KolProfileDto dto
    ) {
        return new ResponseEntity<>(
                service.updateProfileByPrincipal(principal, dto),
                HttpStatus.OK
        );
    }

    @PutMapping("kol/images")
    public ResponseEntity<?> updateImages(
            Principal principal,
            @RequestParam(value = "images", required = false) List<MultipartFile> images
    ) {
        return new ResponseEntity<>(
                service.updateImages(principal, images),
                HttpStatus.OK
        );
    }
    /*
        END PROFILE
     */

    /*
        BOOKING
     */
//    @Get

}
