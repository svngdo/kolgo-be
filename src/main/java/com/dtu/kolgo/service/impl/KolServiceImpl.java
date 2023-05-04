package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.dto.kol.KolDetailsDto;
import com.dtu.kolgo.dto.kol.KolDto;
import com.dtu.kolgo.dto.kol.KolProfileDto;
import com.dtu.kolgo.enums.FieldType;
import com.dtu.kolgo.exception.InvalidException;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.Field;
import com.dtu.kolgo.model.Image;
import com.dtu.kolgo.model.Kol;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.repository.KolRepository;
import com.dtu.kolgo.service.*;
import com.dtu.kolgo.util.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class KolServiceImpl implements KolService {

    @Value("${file.image-path}")
    private String imagePath;

    private final KolRepository repo;
    private final UserService userService;
    private final FieldService fieldService;
    private final ImageService imageService;
    private final CityService cityService;
    private final ModelMapper mapper;

    @Override
    public void save(Kol kol) {
        repo.save(kol);
    }

    @Override
    public List<KolDto> getAllDto() {
        return repo.findAll()
                .stream()
                .map(kol -> mapper.map(kol, KolDto.class))
                .toList();
    }

    @Override
    public List<KolDto> getAllDtoByField(short fieldId) {
        Field field = fieldService.get(fieldId);
        if (field.getType() != FieldType.KOL) {
            throw new InvalidException("Invalid field type");
        }
        return repo.findAllByField(field)
                .stream().map(kol -> mapper.map(kol, KolDto.class)).toList();
    }

    @Override
    public Kol get(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Kol ID not found: " + id));
    }

    @Override
    public Kol get(User user) {
        return repo.findByUser(user)
                .orElseThrow(() -> new NotFoundException("User ID not found: " + user.getId()));
    }

    @Override
    public Kol get(Principal principal) {
        User user = userService.get(principal);
        return get(user);
    }

    @Override
    public KolDetailsDto getDetails(Kol kol) {
        KolDetailsDto dto = mapper.map(kol, KolDetailsDto.class);
        dto.setImages(kol.getImages().stream().map(Image::getName).toList());
        return dto;
    }

    @Override
    public KolDetailsDto getDetails(int id) {
        Kol kol = get(id);
        return getDetails(kol);
    }

    @Override
    public KolDetailsDto getDetails(Principal principal) {
        Kol kol = get(principal);
        return getDetails(kol);
    }

    @Override
    public ApiResponse updateProfile(
            int id, KolProfileDto dto, MultipartFile avatar, List<MultipartFile> images) {
        Kol kol = get(id);

        userService.updateAvatar(kol.getUser(), avatar);
        updateImages(kol, images);

        kol.getUser().setFirstName(dto.getFirstName());
        kol.getUser().setLastName(dto.getLastName());
        kol.setPhone(dto.getPhone());
        kol.setGender(dto.getGender());
        kol.getAddress().setCity(cityService.get(dto.getCityId()));
        kol.getAddress().setDetails(dto.getAddressDetails());
        kol.setField(fieldService.get(dto.getFieldId()));
        kol.setFacebookUrl(dto.getFacebookUrl());
        kol.setInstagramUrl(dto.getInstagramUrl());
        kol.setTiktokUrl(dto.getTiktokUrl());
        kol.setYoutubeUrl(dto.getYoutubeUrl());

        repo.save(kol);

        return new ApiResponse("Update KOL successfully !!");
    }

    @Override
    public ApiResponse updateProfile(
            Principal principal, KolProfileDto dto, MultipartFile avatar, List<MultipartFile> images) {
        Kol kol = get(principal);
        return updateProfile(kol.getId(), dto, avatar, images);
    }

    @Override
    public void updateImages(Kol kol, List<MultipartFile> images) {
        if (images == null) return;
        images.forEach(image -> {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
            imageService.save(new Image(fileName, kol));
            String uploadDir = imagePath;
            FileUtils.saveImage(uploadDir, fileName, image);
        });
    }

    @Override
    public ApiResponse delete(int id) {
        repo.deleteById(id);
        return new ApiResponse("Delete KOL successfully !!");
    }

}
