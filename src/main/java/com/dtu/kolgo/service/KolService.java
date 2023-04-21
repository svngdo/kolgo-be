package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.UpdateKolRequest;
import com.dtu.kolgo.dto.response.KolResponse;
import com.dtu.kolgo.dto.response.WebResponse;
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

    WebResponse update(int kolId, UpdateKolRequest request, MultipartFile avatar, List<MultipartFile> images);

    void updateImages(Kol kol, List<MultipartFile> images);

    WebResponse delete(int kolId);

}
