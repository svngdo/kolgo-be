package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.request.KolRegisterRequest;
import com.dtu.kolgo.dto.request.KolUpdateRequest;
import com.dtu.kolgo.dto.response.KolResponse;
import com.dtu.kolgo.dto.response.WebResponse;
import com.dtu.kolgo.repository.KolRepository;
import com.dtu.kolgo.service.KolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KolServiceImpl implements KolService {

    private final KolRepository repo;

    @Override
    public KolResponse save(KolRegisterRequest request) {
        return null;
    }

    @Override
    public List<KolResponse> fetchAll() {
        return null;
    }

    @Override
    public KolResponse fetch(int id) {
        return null;
    }

    @Override
    public KolResponse update(int id, KolUpdateRequest request) {
        return null;
    }

    @Override
    public WebResponse delete(int id) {
        return null;
    }

}
