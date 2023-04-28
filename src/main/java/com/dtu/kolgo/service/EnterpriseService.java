package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.EntUpdateRequest;
import com.dtu.kolgo.dto.response.ApiResponse;
import com.dtu.kolgo.dto.response.EntResponse;
import com.dtu.kolgo.model.Enterprise;
import com.dtu.kolgo.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

public interface EnterpriseService {

    ApiResponse save(Enterprise ent);

    List<EntResponse> getAllResponses();

    Enterprise get(int entId);

    Enterprise get(User user);

    Enterprise get(Principal principal);

    EntResponse getProfile(int entId);

    EntResponse getProfile(Principal principal);

    ApiResponse updateProfile(int entId, EntUpdateRequest request, MultipartFile avatar);

    ApiResponse updateProfile(Principal principal, EntUpdateRequest request, MultipartFile avatar);

    ApiResponse delete(int entId);

    EntResponse mapEntityToDto(Enterprise enterprise);

    List<EntResponse> mapEntityToDto(List<Enterprise> enterprises);

}
