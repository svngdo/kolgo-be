package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.request.PaymentRequest;
import com.dtu.kolgo.dto.response.ApiResponse;
import com.dtu.kolgo.dto.response.PaymentResponse;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.Payment;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.repository.PaymentRepository;
import com.dtu.kolgo.service.PaymentService;
import com.dtu.kolgo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repo;
    private final UserService userService;

    @Override
    public ApiResponse save(PaymentRequest request) {
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
        return new PaymentResponse(
                payment.getId(),
                payment.getMethod(),
                payment.getTxnRef(),
                payment.getAmount().toString(),
                payment.getDescription(),
                payment.getTxnNo(),
                payment.getBankCode(),
                payment.getDate().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")),
                payment.getStatus(),
                payment.getUser().getId()
        );
    }

    @Override
    public List<PaymentResponse> mapEntitiesToDtos(List<Payment> payments) {
        return payments.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }
}
