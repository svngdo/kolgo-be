package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.PaymentRequest;
import com.dtu.kolgo.dto.response.ApiResponse;
import com.dtu.kolgo.dto.response.PaymentResponse;
import com.dtu.kolgo.model.Payment;

import java.security.Principal;
import java.util.List;

public interface PaymentService {

    ApiResponse save(PaymentRequest request);

    List<Payment> getAll();

    List<PaymentResponse> getAllResponses(Principal principal);

    Payment get(int id);

    PaymentResponse mapEntityToDto(Payment payment);

    List<PaymentResponse> mapEntitiesToDtos(List<Payment> payments);

}
