package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.KolRegisterRequest;
import com.dtu.kolgo.dto.request.KolUpdateRequest;
import com.dtu.kolgo.dto.response.KolResponse;
import com.dtu.kolgo.dto.response.WebResponse;

import java.util.List;

public interface KolService {

    KolResponse save(KolRegisterRequest request);

    List<KolResponse> fetchAll();

    KolResponse fetch(int id);

    KolResponse update(int id, KolUpdateRequest request);

    WebResponse delete(int id);

}
