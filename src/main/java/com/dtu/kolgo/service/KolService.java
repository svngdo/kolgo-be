package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.KolUpdateRequest;
import com.dtu.kolgo.dto.response.ApiResponse;
import com.dtu.kolgo.dto.response.KolResponse;
import com.dtu.kolgo.model.Kol;
import com.dtu.kolgo.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

public interface KolService {

    void save(Kol kol);

    List<KolResponse> getAll();

    List<KolResponse> getAll(short fieldId);

    List<KolResponse> getResponses(List<Kol> kols);

    Kol get(int kolId);

    Kol get(User user);

    Kol get(Principal principal);

    KolResponse getProfile(int kolId);

    KolResponse getProfile(Principal principal);

    ApiResponse updateProfile(int kolId, KolUpdateRequest request, MultipartFile avatar, List<MultipartFile> images);

    ApiResponse updateProfile(Principal principal, KolUpdateRequest request, MultipartFile avatar, List<MultipartFile> images);

    void updateImages(Kol kol, List<MultipartFile> images);

    ApiResponse delete(int kolId);

}
