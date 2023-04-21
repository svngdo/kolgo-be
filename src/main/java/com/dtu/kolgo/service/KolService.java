package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.KolUpdateRequest;
import com.dtu.kolgo.dto.response.KolResponse;
import com.dtu.kolgo.dto.response.ApiResponse;
import com.dtu.kolgo.model.Kol;
import com.dtu.kolgo.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface KolService {

    void save(Kol kol);

    List<KolResponse> getAll();

    List<KolResponse> getAllByFieldId(short fieldId);

    List<KolResponse> getResponseList(List<Kol> kols);

    Kol getById(int kolId);

    Kol getByUser(User user);

    KolResponse getProfileById(int kolId);

    ApiResponse update(int kolId, KolUpdateRequest request, MultipartFile avatar, List<MultipartFile> images);

    void updateImages(Kol kol, List<MultipartFile> images);

    ApiResponse delete(int kolId);

}
