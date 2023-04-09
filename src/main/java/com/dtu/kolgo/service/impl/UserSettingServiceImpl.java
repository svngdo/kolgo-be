package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.request.EmailRequest;
import com.dtu.kolgo.dto.request.UpdateEnterpriseRequest;
import com.dtu.kolgo.dto.request.UpdateKolRequest;
import com.dtu.kolgo.dto.request.UpdatePasswordRequest;
import com.dtu.kolgo.dto.response.EnterpriseResponse;
import com.dtu.kolgo.dto.response.KolResponse;
import com.dtu.kolgo.dto.response.WebResponse;
import com.dtu.kolgo.exception.InvalidException;
import com.dtu.kolgo.model.Kol;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.service.KolService;
import com.dtu.kolgo.service.UserService;
import com.dtu.kolgo.service.UserSettingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserSettingServiceImpl implements UserSettingService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final KolService kolService;

    @Override
    public User getUserByPrincipal(Principal principal) {
        return userService.getByEmail(principal.getName());
    }

    @Override
    public WebResponse updateUserEmail(Principal principal, EmailRequest request) {
        userService.validateEmail(request.getEmail());
        User user = getUserByPrincipal(principal);
        user.setEmail(request.getEmail());
        userService.save(user);

        return new WebResponse("Updated email successfully User with ID: " + user.getId());
    }

    @Override
    public WebResponse updateUserPassword(Principal principal, UpdatePasswordRequest request) {
        User user = getUserByPrincipal(principal);

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new InvalidException("Incorrect paassword");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userService.save(user);

        return new WebResponse("Updated password successfully !!");
    }

    @Override
    public KolResponse getKolProfile(Principal principal) {
        User user = getUserByPrincipal(principal);
        Kol kol = kolService.getByUser(user);
        return kolService.getResponseById(kol.getId());
    }

    @Override
    public WebResponse updateKolProfile(Principal principal, UpdateKolRequest request) {
        User user = getUserByPrincipal(principal);
        Kol kol = kolService.getByUser(user);
        return kolService.update(kol.getId(), request);
    }

    @Override
    public EnterpriseResponse getEnterpriseProfile(Principal principal) {
        return null;
    }

    @Override
    public WebResponse updateEnterpriseProfile(Principal principal, MultipartFile file, UpdateEnterpriseRequest request) {
        return null;
    }

}