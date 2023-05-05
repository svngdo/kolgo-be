package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.VnPayDto;
import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.model.Payment;

import java.util.List;

public interface PaymentService {

    ApiResponse save(VnPayDto request);

    List<Payment> getAll();

    Payment get(int id);

}
