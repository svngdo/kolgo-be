package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.City;
import com.dtu.kolgo.repository.CityRepository;
import com.dtu.kolgo.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository repo;

    @Override
    public List<City> getAll() {
        return repo.findAll();
    }

    @Override
    public City getByAbbreviation(String abbreviation) {
        return repo.findByAbbreviation(abbreviation)
                .orElseThrow(() -> new NotFoundException("Not found City with abbreviation: " + abbreviation));
    }
}
