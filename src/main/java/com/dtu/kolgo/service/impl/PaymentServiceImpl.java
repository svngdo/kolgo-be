package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.payment.VnPayDto;
import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.Payment;
import com.dtu.kolgo.repository.PaymentRepository;
import com.dtu.kolgo.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repo;

    @Override
    public ApiResponse save(VnPayDto request) {
        log.info(request.toString());
//        repo.save(new Payment(
//                request.getMethod(),
//                request.getVnp_TxnRef(),
//                request.getVnp_TransactionNo(),
//                request.getVnp_BankTranNo(),
//                request.getVnp_BankCode(),
//                request.getVnp_Amount(),
//                request.getVnp_OrderInfo(),
//                request.getVnp_PayDate(),
//                request.getu
//        ));
        return new ApiResponse("Saved payment successfully");
    }

    @Override
    public List<Payment> getAll() {
        return repo.findAll();
    }

    @Override
    public Payment get(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found Payment with ID: " + id));
    }

}
