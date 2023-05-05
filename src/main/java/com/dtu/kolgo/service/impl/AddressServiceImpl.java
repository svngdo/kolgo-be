package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.Address;
import com.dtu.kolgo.model.City;
import com.dtu.kolgo.repository.AddressRepository;
import com.dtu.kolgo.service.AddressService;
import com.dtu.kolgo.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repo;
    private final CityService cityService;

    @Override
    public void update(int id, short cityId, String details) {
        Address add = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Address ID not found: " + id));
        City city = cityService.get(cityId);
        add.setCity(city);
        add.setDetails(details);
        repo.save(add);
    }
}
