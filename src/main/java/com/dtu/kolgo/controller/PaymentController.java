package com.dtu.kolgo.controller;

import com.dtu.kolgo.dto.payment.PaymentDto;
import com.dtu.kolgo.service.PaymentService;
import com.dtu.kolgo.vnpay.VnPayService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;
    private final VnPayService vnPayService;

    @PostMapping("payments/vnpay")
    public void vnPayPayment(
            HttpServletRequest request, HttpServletResponse response) {
        vnPayService.doPost(request, response);
    }


    @PostMapping("payments")
    public ResponseEntity<?> createPayment(
            Principal principal,
            @RequestBody @Valid PaymentDto dto
    ) {
       return new ResponseEntity<>(
               service.createPayment(principal, dto),
               HttpStatus.OK
       );
    }

}
