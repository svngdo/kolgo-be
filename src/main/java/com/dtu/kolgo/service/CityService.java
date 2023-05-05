package com.dtu.kolgo.service;

import com.dtu.kolgo.model.City;

import java.util.List;

public interface CityService {

    List<City> getAll();

    City get(short id);

}
