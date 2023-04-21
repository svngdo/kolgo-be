package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.EmailRequest;
import com.dtu.kolgo.dto.request.EntUpdateRequest;
import com.dtu.kolgo.dto.request.KolUpdateRequest;
import com.dtu.kolgo.dto.request.PasswordUpdateRequest;
import com.dtu.kolgo.dto.response.*;
import com.dtu.kolgo.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

public interface UserSettingService {

    User getUserByPrincipal(Principal principal);

    ApiResponse updateUserEmail(Principal principal, EmailRequest request);

    ApiResponse updateUserPassword(Principal principal, PasswordUpdateRequest request);

    KolResponse getKolProfile(Principal principal);

    ApiResponse updateKolProfile(Principal principal, KolUpdateRequest request, MultipartFile avatar, List<MultipartFile> images);

    EntResponse getEnterpriseProfile(Principal principal);

    ApiResponse updateEnterpriseProfile(Principal principal, EntUpdateRequest request, MultipartFile avatar);

    List<BookingResponse> getBookingHistory(Principal principal);

    List<PaymentResponse> getPaymentHistory(Principal principal);

}
