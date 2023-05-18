package com.dtu.kolgo.service;

import com.dtu.kolgo.enums.FieldType;
import com.dtu.kolgo.model.Field;

import java.util.List;

public interface FieldService {

    List<Field> getAll();

    List<Field> getAllByType(FieldType type);

    Field getById(short id);

    List<Short> convertFieldsToIds(List<Field> fields);

    List<String> convertFieldsToNames(List<Field> fields);

}
