package com.dtu.kolgo.controller;

import com.dtu.kolgo.dto.request.CampaignRequest;
import com.dtu.kolgo.service.CampaignService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class CampaignController {

    private final CampaignService service;

    @GetMapping("campaigns")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(
                service.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("campaigns/{id}")
    public ResponseEntity<?> get(
            @PathVariable("id") int id
    ) {
        return new ResponseEntity<>(
                service.getResponse(id),
                HttpStatus.OK
        );
    }

    @PutMapping("campaigns/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") int id,
            @RequestBody @Valid CampaignRequest request
    ) {
        return new ResponseEntity<>(
                service.update(id, request),
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

    @PostMapping("campaigns/ent")
    public ResponseEntity<?> addEntCampaign(
            Principal principal,
            CampaignRequest request
    ) {
        return new ResponseEntity<>(
                service.save(principal, request),
                HttpStatus.OK
        );
    }

    @GetMapping("campaigns/ent")
    public ResponseEntity<?> getEntCampaigns(
            Principal principal
    ) {
        return new ResponseEntity<>(
                service.getAll(principal, true),
                HttpStatus.OK
        );
    }

    @PutMapping("campaigns/{id}/ent")
    public ResponseEntity<?> updateEntCampaign(
            Principal principal,
            @PathVariable("id") int id,
            @RequestBody @Valid CampaignRequest request
    ) {
        return new ResponseEntity<>(
                service.update(principal, id, request),
                HttpStatus.OK
        );
    }

    @DeleteMapping("campaigns/{id}/ent")
    public ResponseEntity<?> deleteEntCampaign(
            Principal principal,
            @PathVariable("id") int id
    ) {
        return new ResponseEntity<>(
                service.delete(principal, id),
                HttpStatus.OK
        );
    }

    @GetMapping("campaigns/kol")
    public ResponseEntity<?> getKolCampaigns(
            Principal principal
    ) {
        return new ResponseEntity<>(
                service.getAll(principal, false),
                HttpStatus.OK
        );
    }

}
