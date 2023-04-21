package com.dtu.kolgo.controller;

import com.dtu.kolgo.dto.request.KolUpdateRequest;
import com.dtu.kolgo.service.KolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("kols")
@RequiredArgsConstructor
public class KolController {

    private final KolService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(
                service.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<?> get(
            @PathVariable("id") int kolId
    ) {
        return new ResponseEntity<>(
                service.getProfileById(kolId),
                HttpStatus.OK
        );
    }

    @GetMapping("field/{fieldId}")
    public ResponseEntity<?> getAllByField(
            @PathVariable("fieldId") short fieldId
    ) {
        return new ResponseEntity<>(
                service.getAllByFieldId(fieldId),
                HttpStatus.OK
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") int kolId,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar,
            @RequestParam(value = "images", required = false) List<MultipartFile> images,
            @RequestBody KolUpdateRequest request
    ) {
        return new ResponseEntity<>(
                service.update(kolId, request, avatar, images),
                HttpStatus.OK
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") int userId
    ) {
        return new ResponseEntity<>(
                service.delete(userId),
                HttpStatus.OK
        );
    }

}
