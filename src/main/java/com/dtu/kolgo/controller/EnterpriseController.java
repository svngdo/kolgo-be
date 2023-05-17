package com.dtu.kolgo.controller;

import com.dtu.kolgo.dto.enterprise.EnterpriseDto;
import com.dtu.kolgo.service.EnterpriseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                service.getDtoById(id),
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

}