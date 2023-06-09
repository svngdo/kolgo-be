package com.dtu.kolgo.controller;

import com.dtu.kolgo.dto.campaign.CampaignDto;
import com.dtu.kolgo.service.CampaignService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CampaignController {

    private final CampaignService service;

    @GetMapping("campaigns")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(
                service.getDtos(),
                HttpStatus.OK
        );
    }

    @GetMapping("campaigns/{id}")
    public ResponseEntity<?> get(
            @PathVariable("id") int id
    ) {
        return new ResponseEntity<>(
                service.getDtoById(id),
                HttpStatus.OK
        );
    }

    @PutMapping("campaigns/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") int id,
            @RequestBody @Valid CampaignDto dto
    ) {
        return new ResponseEntity<>(
                service.update(id, dto),
                HttpStatus.OK
        );
    }

    @DeleteMapping("campaigns/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") int id
    ) {
        return new ResponseEntity<>(
                service.delete(id),
                HttpStatus.OK
        );
    }

}
