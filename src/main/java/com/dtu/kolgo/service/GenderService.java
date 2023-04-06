package com.dtu.kolgo.service;

import com.dtu.kolgo.model.Gender;

import java.util.List;

public interface GenderService {

    List<Gender> getAll();

    Gender get(String name);

}
