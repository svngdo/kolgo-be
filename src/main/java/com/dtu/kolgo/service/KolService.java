package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.UpdateKolRequest;
import com.dtu.kolgo.dto.response.KolResponse;
import com.dtu.kolgo.dto.response.WebResponse;
import com.dtu.kolgo.model.Kol;
import com.dtu.kolgo.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface KolService {

    void save(Kol kol);

    List<KolResponse> getAll();

    Kol getById(int kolId);

    Kol getByUser(User user);

    KolResponse getResponseById(int kolId);

    WebResponse update(int kolId, UpdateKolRequest request);

    void updateImages(Kol kol, Set<MultipartFile> images);

    WebResponse delete(int kolId);

}
