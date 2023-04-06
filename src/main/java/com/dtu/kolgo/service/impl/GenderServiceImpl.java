package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.Gender;
import com.dtu.kolgo.repository.GenderRepository;
import com.dtu.kolgo.service.GenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenderServiceImpl implements GenderService {

    private final GenderRepository repo;

    @Override
    public List<Gender> getAll() {
        return repo.findAll();
    }

    @Override
    public Gender get(String name) {
        return repo.findByName(name)
                .orElseThrow(() -> new NotFoundException("Not found Gender name: " + name));
    }

}
