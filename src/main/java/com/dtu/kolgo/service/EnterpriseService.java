package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.EntUpdateRequest;
import com.dtu.kolgo.dto.response.EntResponse;
import com.dtu.kolgo.dto.response.ApiResponse;
import com.dtu.kolgo.model.Enterprise;
import com.dtu.kolgo.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

public interface EnterpriseService {

    void save(Enterprise ent);

    List<EntResponse> getAll();

    Enterprise getById(int entId);

    Enterprise getByUser(User user);

    EntResponse getProfileById(int entId);

    EntResponse getProfileByPrincipal(Principal principal);

    ApiResponse updateProfileById(int entId, EntUpdateRequest request, MultipartFile avatar);

    ApiResponse updateProfileByPrincipal(Principal principal, EntUpdateRequest request, MultipartFile avatar);

    ApiResponse delete(int entId);

}
