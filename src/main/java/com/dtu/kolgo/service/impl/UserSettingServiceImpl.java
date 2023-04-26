package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.response.BookingResponse;
import com.dtu.kolgo.dto.response.PaymentResponse;
import com.dtu.kolgo.enums.Roles;
import com.dtu.kolgo.model.Booking;
import com.dtu.kolgo.model.Payment;
import com.dtu.kolgo.model.Role;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.service.EnterpriseService;
import com.dtu.kolgo.service.KolService;
import com.dtu.kolgo.service.UserService;
import com.dtu.kolgo.service.UserSettingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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