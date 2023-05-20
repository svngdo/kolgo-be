package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.payment.PaymentDto;
import com.dtu.kolgo.model.Payment;

import java.security.Principal;

public interface PaymentService {

    Payment save(Payment payment);

    PaymentDto createPayment(Principal principal, PaymentDto dto);
}
