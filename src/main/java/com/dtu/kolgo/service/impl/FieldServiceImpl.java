package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.enums.FieldType;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.BaseShort;
import com.dtu.kolgo.model.Field;
import com.dtu.kolgo.repository.FieldRepository;
import com.dtu.kolgo.service.FieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FieldServiceImpl implements FieldService {

    private final FieldRepository repo;

    @Override
    public List<Field> getAll() {
        return repo.findAll();
    }

    @Override
    public List<Field> getAllByType(FieldType type) {
        return repo.findAllByType(type);
    }

    @Override
    public Field getById(short id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException(" Field ID not found: " + id));
    }

    @Override
    public List<Short> convertFieldsToIds(List<Field> fields) {
        return fields.stream().map(BaseShort::getId).toList();
    }

    @Override
    public List<String> convertFieldsToNames(List<Field> fields) {
        return fields.stream().map(Field::getName).toList();
    }
}
