package com.dtu.kolgo.controller;

import com.dtu.kolgo.dto.request.EnterpriseUpdateRequest;
import com.dtu.kolgo.dto.request.KolUpdateRequest;
import com.dtu.kolgo.dto.request.UserEmailRequest;
import com.dtu.kolgo.dto.request.UserPasswordRequest;
import com.dtu.kolgo.model.Enterprise;
import com.dtu.kolgo.model.Kol;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.service.EnterpriseService;
import com.dtu.kolgo.service.KolService;
import com.dtu.kolgo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("settings")
@RequiredArgsConstructor
public class SettingController {

    private final UserService userService;
    private final KolService kolService;
    private final EnterpriseService enterpriseService;

    @PostMapping("email")
    public ResponseEntity<?> updateEmail(
            Principal principal,
            @RequestBody UserEmailRequest request
    ) {
        return new ResponseEntity<>(
                userService.updateEmail(principal, request),
                HttpStatus.OK
        );
    }

    @PostMapping("password")
    public ResponseEntity<?> updatePassword(
            Principal principal,
            @RequestBody UserPasswordRequest request
    ) {
        return new ResponseEntity<>(
                userService.updatePassword(principal, request),
                HttpStatus.OK
        );
    }

    @GetMapping("kol_profile")
    public ResponseEntity<?> getKolProfile(
            Principal principal,
            @RequestBody KolUpdateRequest request
    ) {
        String userId = principal.getName();
        User user = userService.getById(Integer.parseInt(userId));
        Kol kol = kolService.getByUser(user);
        return new ResponseEntity<>(
                kolService.update(kol.getId(), request),
                HttpStatus.OK
        );
    }

    @PostMapping("enterprise_profile")
    public ResponseEntity<?> updateKolProfile(
            Principal principal,
            @RequestBody EnterpriseUpdateRequest request
    ) {
        String userId = principal.getName();
        User user = userService.getById(Integer.parseInt(userId));
        Enterprise ent = enterpriseService.getByUser(user);
        return new ResponseEntity<>(
                enterpriseService.update(ent.getId(), request),
                HttpStatus.OK
        );
    }

}
