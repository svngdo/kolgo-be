package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.request.KolUpdateRequest;
import com.dtu.kolgo.dto.response.KolResponse;
import com.dtu.kolgo.dto.response.WebResponse;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.Kol;
import com.dtu.kolgo.repository.KolRepository;
import com.dtu.kolgo.service.CityService;
import com.dtu.kolgo.service.GenderService;
import com.dtu.kolgo.service.KolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KolServiceImpl implements KolService {

    private final KolRepository repo;
    private final CityService cityService;
    private final GenderService genderService;

    @Override
    public void save(Kol kol) {
        repo.save(kol);
    }

    @Override
    public List<KolResponse> getAll() {
        List<Kol> kols = repo.findAll();
        return kols.stream()
                .map(kol -> KolResponse.builder()
                        .id(kol.getId())
                        .firstName(kol.getUser().getFirstName())
                        .lastName(kol.getUser().getLastName())
                        .avatar(kol.getUser().getAvatar())
                        .city(kol.getCity().getName())
                        .speciality(kol.getSpeciality())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Kol getById(int kolId) {
        return repo.findById(kolId)
                .orElseThrow(() -> new NotFoundException("KOL ID not found: " + kolId));
    }

    @Override
    public KolResponse getResponseById(int kolId) {
        Kol kol = getById(kolId);
        return KolResponse.builder()
                .id(kol.getId())
                .firstName(kol.getUser().getFirstName())
                .lastName(kol.getUser().getLastName())
                .email(kol.getUser().getEmail())
                .gender(kol.getGender().getName())
                .phoneNumber(kol.getPhoneNumber())
                .city(kol.getCity().getName())
                .speciality(kol.getSpeciality())
                .facebookUrl(kol.getFacebookUrl())
                .instagramUrl(kol.getInstagramUrl())
                .tiktokUrl(kol.getTiktokUrl())
                .youtubeUrl(kol.getYoutubeUrl())
                .feedbacks(kol.getFeedbacks())
                .build();
    }

    @Override
    public WebResponse update(int kolId, KolUpdateRequest request) {
        Kol kol = getById(kolId);

        kol.getUser().setFirstName(request.getFirstName());
        kol.getUser().setLastName(request.getLastName());
        kol.getUser().setAvatar(request.getAvatar());
        kol.setPhoneNumber(request.getPhoneNumber());
        kol.setCity(cityService.getByAbbreviation(request.getCity()));
        kol.setGender(genderService.get(request.getGender()));
        kol.setSpeciality(request.getSpeciality());
        kol.setFacebookUrl(request.getFacebookUrl());
        kol.setInstagramUrl(request.getInstagramUrl());
        kol.setTiktokUrl(request.getTiktokUrl());
        kol.setYoutubeUrl(request.getYoutubeUrl());

        repo.save(kol);

        return new WebResponse("Update KOL successfully !!");
    }

    @Override
    public WebResponse delete(int kolId) {
        repo.deleteById(kolId);
        return new WebResponse("Delete KOL successfully !!");
    }

}
