package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.response.ApiResponse;
import com.dtu.kolgo.dto.response.PaymentResponse;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.Payment;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.repository.PaymentRepository;
import com.dtu.kolgo.service.PaymentService;
import com.dtu.kolgo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repo;
    private final UserService userService;

    @Override
    public ApiResponse save(Payment payment) {
        repo.save(payment);
        return new ApiResponse("Saved payment successfully");
    }

    @Override
    public List<Payment> getAll() {
        return repo.findAll();
    }

    @Override
    public List<PaymentResponse> getAllResponses(Principal principal) {
        User user = userService.get(principal);
        return mapEntitiesToDtos(repo.findAllByUser(user));
    }

    @Override
    public Payment get(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found Payment with ID: " + id));
    }

    @Override
    public PaymentResponse mapEntityToDto(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .method(payment.getMethod())
                .amountPaid(payment.getAmountPaid().toString())
                .description(payment.getDescription())
                .status(payment.getStatus())
                .userId(payment.getUser().getId())
                .build();
    }

    @Override
    public List<PaymentResponse> mapEntitiesToDtos(List<Payment> payments) {
        return payments.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }
}
