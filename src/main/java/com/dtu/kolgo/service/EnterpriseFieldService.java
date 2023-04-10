package com.dtu.kolgo.service;

import com.dtu.kolgo.model.EnterpriseField;

import java.util.List;

public interface EnterpriseFieldService {

    List<EnterpriseField> getAll();

    EnterpriseField getById(short entFieldId);

}
