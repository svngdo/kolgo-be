package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.KolField;
import com.dtu.kolgo.repository.KolFieldRepository;
import com.dtu.kolgo.service.KolFieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KolFieldServiceImpl implements KolFieldService {

    private final KolFieldRepository repo;

    @Override
    public List<KolField> getAll() {
        return repo.findAll();
    }

    @Override
    public KolField getById(short kolFieldId) {
        return repo.findById(kolFieldId)
                .orElseThrow(() -> new NotFoundException("Kol Field ID not found: " + kolFieldId));
    }

}
