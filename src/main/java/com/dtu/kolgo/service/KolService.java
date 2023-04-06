package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.KolUpdateRequest;
import com.dtu.kolgo.dto.response.KolResponse;
import com.dtu.kolgo.dto.response.WebResponse;
import com.dtu.kolgo.model.Kol;

import java.util.List;

public interface KolService {

    void save(Kol kol);

    List<KolResponse> getAll();

    Kol getById(int kolId);

    KolResponse getResponseById(int kolId);

    WebResponse update(int kolId, KolUpdateRequest request);

    WebResponse delete(int kolId);

}
