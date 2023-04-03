package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.City;
import com.dtu.kolgo.repository.CityRepository;
import com.dtu.kolgo.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository repo;

    @Override
    public City get(String name) {
        return repo.findByName(name)
                .orElseThrow(() -> new NotFoundException("City not found: " + name));
    }

}
