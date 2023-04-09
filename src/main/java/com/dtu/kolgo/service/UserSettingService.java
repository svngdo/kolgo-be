package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.EmailRequest;
import com.dtu.kolgo.dto.request.UpdateEnterpriseRequest;
import com.dtu.kolgo.dto.request.UpdateKolRequest;
import com.dtu.kolgo.dto.request.UpdatePasswordRequest;
import com.dtu.kolgo.dto.response.EnterpriseResponse;
import com.dtu.kolgo.dto.response.KolResponse;
import com.dtu.kolgo.dto.response.WebResponse;
import com.dtu.kolgo.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

public interface UserSettingService {

    User getUserByPrincipal(Principal principal);

    WebResponse updateUserEmail(Principal principal, EmailRequest request);

    WebResponse updateUserPassword(Principal principal, UpdatePasswordRequest request);

    KolResponse getKolProfile(Principal principal);

    WebResponse updateKolProfile(Principal principal, UpdateKolRequest request);

    EnterpriseResponse getEnterpriseProfile(Principal principal);

    WebResponse updateEnterpriseProfile(Principal principal, MultipartFile file, UpdateEnterpriseRequest request);

}
