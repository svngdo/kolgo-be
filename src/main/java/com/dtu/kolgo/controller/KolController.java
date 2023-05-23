package com.dtu.kolgo.controller;

import com.dtu.kolgo.dto.booking.BookingDto;
import com.dtu.kolgo.dto.kol.KolDto;
import com.dtu.kolgo.service.KolService;
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
public class KolController {

    private final KolService service;

    @GetMapping("kols")
    public ResponseEntity<?> getAll(
            @RequestParam(name = "fieldIds", required = false) List<Short> fieldIds
    ) {
        if (fieldIds != null && !fieldIds.isEmpty()) {
            return new ResponseEntity<>(service.getDtosByFieldIds(fieldIds), HttpStatus.OK);
        }
        return new ResponseEntity<>(service.getDtos(), HttpStatus.OK);
    }

    @GetMapping("kols/{id}")
    public ResponseEntity<?> getDetails(
            @PathVariable("id") int id
    ) {
        return new ResponseEntity<>(
                service.getDetailsById(id),
                HttpStatus.OK
        );
    }

    @PutMapping("kols/{id}")
    public ResponseEntity<?> updateKol(
            @PathVariable("id") int id,
            @RequestBody @Valid KolDto dto
    ) {
        return new ResponseEntity<>(
                service.updateById(id, dto),
                HttpStatus.OK
        );
    }

    @GetMapping("kols/{id}/bookings")
    public ResponseEntity<?> getBookings(
            @PathVariable("id") int id
    ) {
        return new ResponseEntity<>(
                service.getBookingsById(id),
                HttpStatus.OK
        );
    }

    @PostMapping("kols/{id}/bookings")
    public ResponseEntity<?> createBooking(
            Principal principal,
            @PathVariable("id") int id,
            @RequestBody @Valid BookingDto dto
    ) {
        return new ResponseEntity<>(
                service.createBooking(principal, id, dto),
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
                service.getDtoByPrincipal(principal),
                HttpStatus.OK
        );
    }

    @PutMapping("kol/profile")
    public ResponseEntity<?> updateProfile(
            Principal principal,
            @RequestBody @Valid KolDto dto
    ) {
        return new ResponseEntity<>(
                service.updateByPrincipal(principal, dto),
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
    @PutMapping("kol/campaigns/{campaignId}/join")
    public ResponseEntity<?> joinCampaign(
            Principal principal,
            @PathVariable("campaignId") int campaignId
    ) {
        return new ResponseEntity<>(
                service.joinCampaign(principal, campaignId),
                HttpStatus.OK
        );
    }

    @PutMapping("kol/campaigns/{campaignId}/quit")
    public ResponseEntity<?> quitCampaign(
            Principal principal,
            @PathVariable("campaignId") int campaignId
    ) {
        return new ResponseEntity<>(
                service.quitCampaign(principal, campaignId),
                HttpStatus.OK
        );
    }

}
