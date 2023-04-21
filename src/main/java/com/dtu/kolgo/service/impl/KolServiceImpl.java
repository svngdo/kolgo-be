package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.request.KolUpdateRequest;
import com.dtu.kolgo.dto.response.FeedbackResponse;
import com.dtu.kolgo.dto.response.ImageResponse;
import com.dtu.kolgo.dto.response.KolResponse;
import com.dtu.kolgo.dto.response.ApiResponse;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.*;
import com.dtu.kolgo.repository.KolRepository;
import com.dtu.kolgo.service.*;
import com.dtu.kolgo.util.FileUtils;
import com.dtu.kolgo.util.env.FileEnv;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class KolServiceImpl implements KolService {

    private final KolRepository repo;
    private final UserService userService;
    private final CityService cityService;
    private final GenderService genderService;
    private final ImageService imageService;
    private final KolFieldService kolFieldService;
    private final FileUtils fileUtils;

    @Override
    public void save(Kol kol) {
        repo.save(kol);
    }

    @Override
    public List<KolResponse> getAll() {
        List<Kol> kols = repo.findAll();
        return getResponseList(kols);
    }

    @Override
    public List<KolResponse> getAllByFieldId(short fieldId) {
        KolField field = kolFieldService.getById(fieldId);
        List<Kol> kols = repo.findAllByField(field);
        return getResponseList(kols);
    }

    @Override
    public List<KolResponse> getResponseList(List<Kol> kols) {
        return kols.stream()
                .map(kol -> KolResponse.builder()
                        .userId(kol.getUser().getId())
                        .firstName(kol.getUser().getFirstName())
                        .lastName(kol.getUser().getLastName())
                        .avatar(kol.getUser().getAvatar() == null ? null : kol.getUser().getAvatar())
                        .kolId(kol.getId())
                        .cityId(kol.getCity() == null ? null : kol.getCity().getId())
                        .kolFieldId(kol.getField() == null ? null : kol.getField().getId())
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
    public KolResponse getProfileById(int kolId) {
        Kol kol = getById(kolId);
        List<ImageResponse> images = kol.getImages().stream()
                .map(img -> new ImageResponse(img.getName()))
                .collect(Collectors.toList());
        List<FeedbackResponse> feedbacks = kol.getFeedbacks().stream()
                .map(feedback -> new FeedbackResponse(
                        feedback.getRating(),
                        feedback.getComment(),
                        feedback.getEnterprise().getName())
                )
                .collect(Collectors.toList());

        return KolResponse.builder()
                .userId(kol.getUser().getId())
                .firstName(kol.getUser().getFirstName())
                .lastName(kol.getUser().getLastName())
                .email(kol.getUser().getEmail())
                .avatar(kol.getUser().getAvatar())
                .phoneNumber(kol.getUser().getPhoneNumber() == null ? null : kol.getUser().getPhoneNumber())
                .kolId(kolId)
                .genderId(kol.getGender() == null ? null : kol.getGender().getId())
                .cityId(kol.getCity() == null ? null : kol.getCity().getId())
                .kolFieldId(kol.getField() == null ? null : kol.getField().getId())
                .facebookUrl(kol.getFacebookUrl())
                .instagramUrl(kol.getInstagramUrl())
                .tiktokUrl(kol.getTiktokUrl())
                .youtubeUrl(kol.getYoutubeUrl())
                .images(images)
                .feedbacks(feedbacks)
                .build();
    }

    @Override
    public ApiResponse update(int kolId, KolUpdateRequest request, MultipartFile avatar, List<MultipartFile> images) {
        Kol kol = getById(kolId);
        City city = cityService.getById(request.getCityId());
        Gender gender = genderService.getById(request.getGenderId());
        KolField field = kolFieldService.getById(request.getKolFieldId());

        userService.updateAvatar(kol.getUser(), avatar);
        updateImages(kol, images);
        kol.getUser().setFirstName(request.getFirstName());
        kol.getUser().setLastName(request.getLastName());
        kol.getUser().setPhoneNumber(request.getPhoneNumber());
        kol.setCity(city);
        kol.setGender(gender);
        kol.setField(field);
        kol.setFacebookUrl(request.getFacebookUrl());
        kol.setInstagramUrl(request.getInstagramUrl());
        kol.setTiktokUrl(request.getTiktokUrl());
        kol.setYoutubeUrl(request.getYoutubeUrl());

        repo.save(kol);

        return new ApiResponse("Update KOL successfully !!");
    }

    @Override
    public void updateImages(Kol kol, List<MultipartFile> images) {
        if (images == null) return;
        images.forEach(image -> {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
            imageService.save(new Image(fileName, kol));
            String uploadDir = FileEnv.IMAGE_PATH + "/" + kol.getUser().getId() + " - " + kol.getUser().getEmail();
            fileUtils.saveImage(uploadDir, fileName, image);
        });
    }

    @Override
    public ApiResponse delete(int kolId) {
        repo.deleteById(kolId);
        return new ApiResponse("Delete KOL successfully !!");
    }

}
