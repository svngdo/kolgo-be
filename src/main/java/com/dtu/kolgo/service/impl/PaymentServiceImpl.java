package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.model.Payment;
import com.dtu.kolgo.repository.PaymentRepository;
import com.dtu.kolgo.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repo;

    @Override
    public Payment save(Payment payment) {
        return repo.save(payment);
    }
}
