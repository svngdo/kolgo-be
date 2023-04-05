package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.KolUpdateRequest;
import com.dtu.kolgo.dto.response.KolResponse;
import com.dtu.kolgo.dto.response.WebResponse;
import com.dtu.kolgo.model.Kol;

import java.util.List;

public interface KolService {

    List<KolResponse> getAll();

    Kol getByUserID(long userId);

    KolResponse getResponseByUserId(long userId);

    WebResponse update(long userId, KolUpdateRequest request);

    WebResponse delete(long userId);

    KolResponse mapEntityToDto(Kol kol);

}
