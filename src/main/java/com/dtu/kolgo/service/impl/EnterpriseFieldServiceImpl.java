package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.EnterpriseField;
import com.dtu.kolgo.repository.EnterpriseFieldRepository;
import com.dtu.kolgo.service.EnterpriseFieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnterpriseFieldServiceImpl implements EnterpriseFieldService {

    private final EnterpriseFieldRepository repo;

    @Override
    public List<EnterpriseField> getAll() {
        return repo.findAll();
    }

    @Override
    public EnterpriseField getById(short entFieldId) {
        return repo.findById(entFieldId)
                .orElseThrow(() -> new NotFoundException("Enterprise Field ID not found: " + entFieldId));
    }
}
