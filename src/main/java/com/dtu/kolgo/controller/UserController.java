package com.dtu.kolgo.controller;

import com.dtu.kolgo.dto.feedback.FeedbackDto;
import com.dtu.kolgo.dto.payment.VnPayDto;
import com.dtu.kolgo.dto.user.PasswordUpdateDto;
import com.dtu.kolgo.dto.user.UserDto;
import com.dtu.kolgo.enums.BookingStatus;
import com.dtu.kolgo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("users")
    public ResponseEntity<?> getAll() {
        List<UserDto> users = service.getDtos();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<?> getUser(
            @PathVariable("id") int userId
    ) {
        return new ResponseEntity<>(
                service.getDtoById(userId),
                HttpStatus.OK
        );
    }

    @PutMapping("users/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable("id") int id,
            @RequestBody @Valid UserDto dto
    ) {
        return new ResponseEntity<>(
                service.updateById(id, dto),
                HttpStatus.OK
        );
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<?> deleteUser(
            @PathVariable("id") int userId
    ) {
        return new ResponseEntity<>(
                service.deleteById(userId),
                HttpStatus.OK
        );
    }

    @GetMapping("user/bookings")
    public ResponseEntity<?> getBookings(
            Principal principal
    ) {
        return new ResponseEntity<>(
                service.getBookingsByPrincipal(principal),
                HttpStatus.OK
        );
    }

    @GetMapping("user/bookings/{bookingId}")
    public ResponseEntity<?> getBooking(
            Principal principal,
            @PathVariable("bookingId") int bookingId
    ) {
        return new ResponseEntity<>(
                service.getBookingByPrincipal(principal, bookingId),
                HttpStatus.OK
        );
    }

    @PutMapping("user/bookings/{bookingId}")
    public ResponseEntity<?> updateBookingStatus(
            Principal principal,
            @RequestParam("status") BookingStatus status,
            @PathVariable("bookingId") int bookingId
    ) {
        return new ResponseEntity<>(
                service.updateBookingStatus(principal, bookingId, status),
                HttpStatus.OK
        );
    }

    @PostMapping("user/bookings/{bookingId}/payments/vnpay")
    public ResponseEntity<?> addBookingPayment(
            Principal principal,
            @PathVariable("bookingId") int bookingId,
            @RequestBody @Valid VnPayDto vnPayDto
    ) {
        return new ResponseEntity<>(
                service.addBookingVnPayPayment(principal, bookingId, vnPayDto),
                HttpStatus.OK
        );
    }

    @PostMapping("user/bookings/{bookingId}/feedbacks")
    public ResponseEntity<?> addBookingFeedback(
            Principal principal,
            @PathVariable("bookingId") int bookingId,
            @RequestBody @Valid FeedbackDto feedbackDto
    ) {
        return new ResponseEntity<>(
                service.addBookingFeedback(principal, bookingId, feedbackDto),
                HttpStatus.OK
        );
    }

    @GetMapping("user/payments")
    public ResponseEntity<?> getPayments(
            Principal principal
    ) {
        return new ResponseEntity<>(
                service.getPaymentsByPrincipal(principal),
                HttpStatus.OK
        );
    }

    @PutMapping("user/email")
    public ResponseEntity<?> updateEmail(
            Principal principal,
            @RequestParam("email") String email
    ) {
        return new ResponseEntity<>(
                service.updateEmail(principal, email),
                HttpStatus.OK
        );
    }

    @PutMapping("user/password")
    public ResponseEntity<?> putPassword(
            Principal principal,
            @RequestBody @Valid PasswordUpdateDto request
    ) {
        return new ResponseEntity<>(
                service.updatePassword(principal, request),
                HttpStatus.OK
        );
    }

    @PutMapping("user/avatar")
    public ResponseEntity<?> updateAvatar(
            Principal principal,
            @RequestParam("avatar") MultipartFile avatar
    ) {
        return new ResponseEntity<>(
                service.updateAvatar(principal, avatar),
                HttpStatus.OK
        );
    }

}
