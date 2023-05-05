package com.dtu.kolgo.service;

import com.dtu.kolgo.enums.FieldType;
import com.dtu.kolgo.model.Field;

import java.util.List;

public interface FieldService {

    List<Field> getAll();

    List<Field> getAll(FieldType type);

    Field get(short id);

}
