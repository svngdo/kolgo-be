package com.dtu.kolgo.controller;

import com.dtu.kolgo.dto.CampaignCreateDto;
import com.dtu.kolgo.dto.CampaignDto;
import com.dtu.kolgo.dto.enterprise.EnterpriseDto;
import com.dtu.kolgo.service.EnterpriseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EnterpriseController {

    private final EnterpriseService service;

    @GetMapping("ents")
    public ResponseEntity<?> getAll(
            @RequestParam(name = "fieldIds", required = false) List<Short> fieldIds
    ) {
        if (fieldIds != null && !fieldIds.isEmpty()) {
            return new ResponseEntity<>(service.getDtosByFieldIds(fieldIds), HttpStatus.OK);
        }
        return new ResponseEntity<>(service.getDtos(), HttpStatus.OK);
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

    @PutMapping("ents/{id}")
    public ResponseEntity<?> updateEnterprise(
            @PathVariable("id")int id,
            @RequestBody @Valid EnterpriseDto dto
    ) {
        return new ResponseEntity<>(
                service.updateById(id, dto),
                HttpStatus.OK
        );
    }

    @GetMapping("ent/profile")
    public ResponseEntity<?> getProfile(
            Principal principal
    ) {
        return new ResponseEntity<>(
                service.getDtoByPrincipal(principal),
                HttpStatus.OK
        );
    }

    @PutMapping("ent/profile")
    public ResponseEntity<?> putProfile(
            Principal principal,
            @RequestBody EnterpriseDto dto
    ) {
        return new ResponseEntity<>(
                service.updateByPrincipal(principal, dto),
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

    @PostMapping("ent/campaigns")
    public ResponseEntity<?> createCampaign(
            Principal principal,
            @RequestParam("images") List<MultipartFile> images,
            @ModelAttribute @Valid CampaignCreateDto campaignCreateDto
    ) {
        System.out.println("CREATE CAMPAIGN");
        return new ResponseEntity<>(
                service.createCampaign(principal, campaignCreateDto, images),
                HttpStatus.OK
        );
    }

    @GetMapping("ent/campaigns")
    public ResponseEntity<?> getAll(
            Principal principal
    ) {
        return new ResponseEntity<>(
                service.getCampaignDtos(principal),
                HttpStatus.OK
        );
    }

    @GetMapping("ent/campaigns/{campaignId}")
    public ResponseEntity<?> get(
            Principal principal,
            @PathVariable("campaignId") int campaignId
    ) {
        return new ResponseEntity<>(
                service.getCampaignDtoByPrincipal(principal, campaignId),
                HttpStatus.OK
        );
    }

    @PutMapping("ent/campaigns/{campaignId}")
    public ResponseEntity<?> updateCampaign(
            Principal principal,
            @PathVariable("campaignId") int campaignId,
            @RequestBody @Valid CampaignDto campaignDto
    ) {
        return new ResponseEntity<>(
                service.updateCampaign(principal, campaignId, campaignDto),
                HttpStatus.OK
        );
    }

    @DeleteMapping("ent/campaigns/{campaignId}")
    public ResponseEntity<?> deleteCampaign(
            Principal principal,
            @PathVariable("campaignId") int campaignId
    ) {
        return new ResponseEntity<>(
                service.deleteCampaign(principal, campaignId),
                HttpStatus.OK
        );
    }


}