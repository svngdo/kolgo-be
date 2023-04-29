package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.request.KolUpdateRequest;
import com.dtu.kolgo.dto.response.ApiResponse;
import com.dtu.kolgo.dto.response.FeedbackResponse;
import com.dtu.kolgo.dto.response.ImageResponse;
import com.dtu.kolgo.dto.response.KolResponse;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.*;
import com.dtu.kolgo.repository.KolRepository;
import com.dtu.kolgo.service.*;
import com.dtu.kolgo.util.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class KolServiceImpl implements KolService {

    @Value("${file.image-path}")
    private String imagePath;
    private final KolRepository repo;
    private final UserService userService;
    private final CityService cityService;
    private final GenderService genderService;
    private final ImageService imageService;
    private final KolFieldService kolFieldService;
    private final FileUtils fileUtils;

    @Override
    public ApiResponse save(Kol kol) {
        repo.save(kol);
        return new ApiResponse("Saved Kol successfully");
    }

    @Override
    public List<KolResponse> getAllResponses() {
        return mapEntityToDto(repo.findAll());
    }

    @Override
    public List<KolResponse> getAllResponses(short fieldId) {
        KolField field = kolFieldService.getById(fieldId);
        List<Kol> kols = repo.findAllByField(field);
        return mapEntityToDto(kols);
    }

    @Override
    public Kol get(int kolId) {
        return repo.findById(kolId)
                .orElseThrow(() -> new NotFoundException("Not found KOL with ID: " + kolId));
    }

    @Override
    public Kol get(User user) {
        return repo.findByUser(user)
                .orElseThrow(() -> new NotFoundException("Not found KOL with User ID: " + user.getId()));
    }

    @Override
    public Kol get(Principal principal) {
        User user = userService.get(principal);
        return get(user);
    }

    @Override
    public KolResponse getProfile(int kolId) {
        Kol kol = get(kolId);
        return mapEntityToDto(kol);
    }

    @Override
    public KolResponse getProfile(Principal principal) {
        Kol kol = get(principal);
        return getProfile(kol.getId());
    }

    @Override
    public ApiResponse updateProfile(
            int kolId, KolUpdateRequest request, MultipartFile avatar, List<MultipartFile> images) {
        Kol kol = get(kolId);
        City city = cityService.getById(request.getCityId());
        Gender gender = genderService.getById(request.getGenderId());
        KolField field = kolFieldService.getById(request.getKolFieldId());

        if (avatar != null) {
            userService.updateAvatar(kol.getUser(), avatar);
        }
        if (images != null) {
            updateImages(kol, images);
        }

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
    public ApiResponse updateProfile(
            Principal principal, KolUpdateRequest request, MultipartFile avatar, List<MultipartFile> images) {
        Kol kol = get(principal);
        return updateProfile(kol.getId(), request, avatar, images);
    }

    @Override
    public void updateImages(Kol kol, List<MultipartFile> images) {
        if (images == null) return;
        images.forEach(image -> {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
            imageService.save(new Image(fileName, kol));
            String uploadDir = imagePath;
            fileUtils.saveImage(uploadDir, fileName, image);
        });
    }

    @Override
    public ApiResponse delete(int kolId) {
        repo.deleteById(kolId);
        return new ApiResponse("Delete KOL successfully !!");
    }

    @Override
    public KolResponse mapEntityToDto(Kol kol) {
        List<ImageResponse> images = kol.getImages().stream()
                .map(img -> new ImageResponse(img.getName()))
                .collect(Collectors.toList());
        List<FeedbackResponse> feedbacks = kol.getUser().getFeedbacks().stream()
                .map(feedback -> FeedbackResponse.builder()
                        .id(feedback.getId())
                        .rating(feedback.getRating())
                        .comment(feedback.getComment())
                        .firstName(feedback.getUser().getFirstName())
                        .lastName(feedback.getUser().getLastName())
                        .rating(feedback.getRating())
                        .comment(feedback.getComment())
                        .build())
                .collect(Collectors.toList());

        return KolResponse.builder()
                .id(kol.getId())
                .userId(kol.getUser().getId())
                .firstName(kol.getUser().getFirstName())
                .lastName(kol.getUser().getLastName())
                .email(kol.getUser().getEmail())
                .avatar(kol.getUser().getAvatar())
                .phoneNumber(kol.getUser().getPhoneNumber() == null ? null : kol.getUser().getPhoneNumber())
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
    public List<KolResponse> mapEntityToDto(List<Kol> kols) {
        return kols.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

}
