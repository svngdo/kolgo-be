package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.request.EmailRequest;
import com.dtu.kolgo.dto.request.UpdateEnterpriseRequest;
import com.dtu.kolgo.dto.request.UpdateKolRequest;
import com.dtu.kolgo.dto.request.UpdatePasswordRequest;
import com.dtu.kolgo.dto.response.*;
import com.dtu.kolgo.exception.InvalidException;
import com.dtu.kolgo.model.*;
import com.dtu.kolgo.service.EnterpriseService;
import com.dtu.kolgo.service.KolService;
import com.dtu.kolgo.service.UserService;
import com.dtu.kolgo.service.UserSettingService;
import com.dtu.kolgo.util.constant.Roles;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserSettingServiceImpl implements UserSettingService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final KolService kolService;
    private final EnterpriseService entService;

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
        return kolService.getProfileById(kol.getId());
    }

    @Override
    public WebResponse updateKolProfile(Principal principal, UpdateKolRequest request, MultipartFile avatar, List<MultipartFile> images) {
        User user = getUserByPrincipal(principal);
        Kol kol = kolService.getByUser(user);
        return kolService.update(kol.getId(), request, avatar, images);
    }

    @Override
    public EnterpriseResponse getEnterpriseProfile(Principal principal) {
        User user = getUserByPrincipal(principal);
        Enterprise ent = entService.getByUser(user);
        return entService.getProfileById(ent.getId());
    }

    @Override
    public WebResponse updateEnterpriseProfile(Principal principal, UpdateEnterpriseRequest request, MultipartFile avatar) {
        User user = getUserByPrincipal(principal);
        Enterprise ent = entService.getByUser(user);
        return entService.update(ent.getId(), request);
    }

    @Override
    public List<BookingResponse> getBookingHistory(Principal principal) {
        User user = getUserByPrincipal(principal);
        List<Role> userRoles = user.getRoles();
        List<Booking> bookings = new ArrayList<>();

        if (userRoles.get(0).getName().equals(Roles.KOL.name())) {
            bookings = kolService.getByUser(user).getBookings();
        } else if (userRoles.get(0).getName().equals(Roles.ENTERPRISE.name())) {
            bookings = entService.getByUser(user).getBookings();
        }
        return bookings.stream().map(booking -> BookingResponse.builder()
                        .time(booking.getTime())
                        .cost(booking.getCost())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentResponse> getPaymentHistory(Principal principal) {
        User user = getUserByPrincipal(principal);
        List<Role> userRoles = user.getRoles();
        List<Payment> payments = new ArrayList<>();

        if (userRoles.get(0).getName().equals(Roles.KOL.name())) {
            payments = kolService.getByUser(user).getPayments();
        } else if (userRoles.get(0).getName().equals(Roles.ENTERPRISE.name())) {
            payments = entService.getByUser(user).getPayments();
        }

        return payments.stream()
                .map(payment -> PaymentResponse.builder()
                        .paymentId(payment.getId())
                        .amount(payment.getAmount())
                        .senderName(payment.getEnterprise().getName())
                        .senderEmail(payment.getEnterprise().getUser().getEmail())
                        .receiverName(payment.getKol().getUser().getFirstName() + " " + payment.getKol().getUser().getLastName())
                        .receiverEmail(payment.getKol().getUser().getEmail())
                        .build())
                .collect(Collectors.toList());
    }

}