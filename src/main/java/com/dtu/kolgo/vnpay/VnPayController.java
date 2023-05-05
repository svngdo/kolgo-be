package com.dtu.kolgo.vnpay;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("vnpay")
public class VnPayController {

    private final VnPayService service;

    @PostMapping("payment")
    public void vnpayPayment(
            HttpServletRequest request, HttpServletResponse response) {
        service.doPost(request, response);
    }

}