package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.model.Image;
import com.dtu.kolgo.repository.ImageRepository;
import com.dtu.kolgo.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository repo;

    @Override
    public Image save(Image image) {
        if (repo.existsByName(image.getName())) {
            return repo.findByName(image.getName());
        }
        return repo.save(image);
    }

}
