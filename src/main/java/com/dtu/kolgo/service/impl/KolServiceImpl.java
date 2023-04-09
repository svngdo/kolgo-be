package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.request.UpdateKolRequest;
import com.dtu.kolgo.dto.response.KolResponse;
import com.dtu.kolgo.dto.response.WebResponse;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.*;
import com.dtu.kolgo.repository.KolRepository;
import com.dtu.kolgo.service.*;
import com.dtu.kolgo.util.FileUtils;
import com.dtu.kolgo.util.env.FileEnv;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KolServiceImpl implements KolService {

    private final KolRepository repo;
    private final UserService userService;
    private final CityService cityService;
    private final GenderService genderService;
    private final ImageService imageService;
    private final FileUtils fileUtils;

    @Override
    public void save(Kol kol) {
        repo.save(kol);
    }

    @Override
    public List<KolResponse> getAll() {
        List<Kol> kols = repo.findAll();
        return kols.stream()
                .map(kol -> KolResponse.builder()
                        .kolId(kol.getId())
                        .firstName(kol.getUser().getFirstName())
                        .lastName(kol.getUser().getLastName())
                        .avatar(kol.getUser().getAvatar())
                        .city(kol.getCity() == null ? null : kol.getCity().getName())
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
    public Kol getByUser(User user) {
        return repo.findByUser(user)
                .orElseThrow(() -> new NotFoundException("KOL with User ID not found: " + user.getId()));
    }

    @Override
    public KolResponse getResponseById(int kolId) {
        Kol kol = getById(kolId);
        String firstName = kol.getUser().getFirstName();
        String lastName = kol.getUser().getLastName();
        String email = kol.getUser().getEmail();
        String avatar = kol.getUser().getAvatar();
        String phoneNumber = kol.getPhoneNumber();
        String gender = kol.getGender() == null ? null : kol.getGender().getName();
        String city = kol.getCity() == null ? null : kol.getCity().getName();
        String speciality = kol.getSpeciality();
        String facebookUrl = kol.getFacebookUrl();
        String instagramUrl = kol.getInstagramUrl();
        String tiktokUrl = kol.getTiktokUrl();
        String youtubeUrl = kol.getYoutubeUrl();
        List<String> images = kol.getImages().stream().map(Image::getName).collect(Collectors.toList());
        List<Feedback> feedbacks = kol.getFeedbacks();

        return KolResponse.builder()
                .kolId(kolId)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .avatar(avatar)
                .phoneNumber(phoneNumber)
                .gender(gender)
                .city(city)
                .speciality(speciality)
                .facebookUrl(facebookUrl)
                .instagramUrl(instagramUrl)
                .tiktokUrl(tiktokUrl)
                .youtubeUrl(youtubeUrl)
                .images(images)
                .feedbacks(feedbacks)
                .build();
    }

    @Override
    public WebResponse update(int kolId, UpdateKolRequest request) {
        Kol kol = getById(kolId);
        City city = cityService.getById(request.getCityId());
        Gender gender = genderService.getById(request.getGenderId());
        userService.updateAvatar(kol.getUser(), request.getAvatar());
        updateImages(kol, request.getImages());

        kol.getUser().setFirstName(request.getFirstName());
        kol.getUser().setLastName(request.getLastName());
        kol.setPhoneNumber(request.getPhoneNumber());
        kol.setCity(city);
        kol.setGender(gender);
        kol.setSpeciality(request.getSpeciality());
        kol.setFacebookUrl(request.getFacebookUrl());
        kol.setInstagramUrl(request.getInstagramUrl());
        kol.setTiktokUrl(request.getTiktokUrl());
        kol.setYoutubeUrl(request.getYoutubeUrl());

        repo.save(kol);

        return new WebResponse("Update KOL successfully !!");
    }

    @Override
    public void updateImages(Kol kol, Set<MultipartFile> images) {
        if (images == null) return;
        images.forEach(image -> {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
            imageService.save(new Image(fileName, kol));
            String uploadDir = FileEnv.IMAGE_PATH + "/" + kol.getUser().getId() + " - " + kol.getUser().getEmail();
            fileUtils.saveImage(uploadDir, fileName, image);
        });
    }

    @Override
    public WebResponse delete(int kolId) {
        repo.deleteById(kolId);
        return new WebResponse("Delete KOL successfully !!");
    }

}
