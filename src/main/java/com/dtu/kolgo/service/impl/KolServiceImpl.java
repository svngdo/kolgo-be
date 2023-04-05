package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.request.KolUpdateRequest;
import com.dtu.kolgo.dto.response.KolResponse;
import com.dtu.kolgo.dto.response.WebResponse;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.Kol;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.repository.KolRepository;
import com.dtu.kolgo.service.KolService;
import com.dtu.kolgo.service.UserService;
import com.dtu.kolgo.util.constant.Gender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KolServiceImpl implements KolService {

    private final KolRepository repo;
    private final UserService userService;

    @Override
    public List<KolResponse> getAll() {
        List<Kol> kols = repo.findAll();
        return kols.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Kol getByUserID(long userId) {
        User user = userService.getById(userId);
        return repo.findByUser(user)
                .orElseThrow(() -> new NotFoundException("Not found user with ID: " + userId));
    }

    @Override
    public KolResponse getResponseByUserId(long userId) {
        Kol kol = getByUserID(userId);
        return mapEntityToDto(kol);
    }

    @Override
    public WebResponse update(long userId, KolUpdateRequest request) {
        User user = userService.getById(userId);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        Kol kol = getByUserID(userId);
        kol.setPhoneNumber(request.getPhoneNumber());
        kol.setCity(request.getCity());
        kol.setGender(request.getGender() != null ? Gender.valueOf(request.getGender()) : null);
        kol.setSpeciality(request.getSpeciality());
        kol.setFacebookUrl(request.getFacebookUrl());
        kol.setInstagramUrl(request.getInstagramUrl());
        kol.setTiktokUrl(request.getTiktokUrl());
        kol.setYoutubeUrl(request.getYoutubeUrl());

        userService.save(user);
        repo.save(kol);

        return new WebResponse("Update KOL successfully !!");
    }

    @Override
    public WebResponse delete(long userId) {
        User user = userService.getById(userId);
        repo.deleteByUser(user);
        return new WebResponse("Delete KOL successfully !!");
    }

    @Override
    public KolResponse mapEntityToDto(Kol kol) {
        return KolResponse.builder()
                .id(kol.getId())
                .firstName(kol.getUser().getFirstName())
                .lastName(kol.getUser().getLastName())
                .email(kol.getUser().getEmail())
                .roles(kol.getUser().getRoles())
                .gender(kol.getGender())
                .speciality(kol.getSpeciality())
                .phoneNumber(kol.getPhoneNumber())
                .city(kol.getCity())
                .facebookUrl(kol.getFacebookUrl())
                .instagramUrl(kol.getInstagramUrl())
                .tiktokUrl(kol.getTiktokUrl())
                .youtubeUrl(kol.getYoutubeUrl())
                .build();
    }

}
