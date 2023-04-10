package com.dtu.kolgo.service;

import com.dtu.kolgo.model.KolField;

import java.util.List;

public interface KolFieldService {

    List<KolField> getAll();

    KolField getById(short kolFieldId);

}
