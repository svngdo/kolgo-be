package com.dtu.kolgo.controller;

import com.dtu.kolgo.vnpay.VnPayService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final VnPayService vnPayService;

    @PostMapping("payments/vnpay")
    public void vnPayPayment(
            HttpServletRequest request, HttpServletResponse response) {
        vnPayService.doPost(request, response);
    }

}
