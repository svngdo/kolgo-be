package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.EmailRequest;
import com.dtu.kolgo.dto.request.UpdateEnterpriseRequest;
import com.dtu.kolgo.dto.request.UpdateKolRequest;
import com.dtu.kolgo.dto.request.UpdatePasswordRequest;
import com.dtu.kolgo.dto.response.*;
import com.dtu.kolgo.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

public interface UserSettingService {

    User getUserByPrincipal(Principal principal);

    WebResponse updateUserEmail(Principal principal, EmailRequest request);

    WebResponse updateUserPassword(Principal principal, UpdatePasswordRequest request);

    KolResponse getKolProfile(Principal principal);

    WebResponse updateKolProfile(Principal principal, UpdateKolRequest request, MultipartFile avatar, List<MultipartFile> images);

    EnterpriseResponse getEnterpriseProfile(Principal principal);

    WebResponse updateEnterpriseProfile(Principal principal, UpdateEnterpriseRequest request, MultipartFile avatar);

    List<BookingResponse> getBookingHistory(Principal principal);

    List<PaymentResponse> getPaymentHistory(Principal principal);

}
