package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.response.BookingResponse;
import com.dtu.kolgo.dto.response.PaymentResponse;
import com.dtu.kolgo.model.User;

import java.security.Principal;
import java.util.List;

public interface UserSettingService {

    User getUserByPrincipal(Principal principal);

    List<BookingResponse> getBookingHistory(Principal principal);

    List<PaymentResponse> getPaymentHistory(Principal principal);

}
