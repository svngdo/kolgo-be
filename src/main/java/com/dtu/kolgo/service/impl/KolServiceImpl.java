package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.ApiResponse;
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
import java.util.*;

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
    public List<KolDto> getAllDtoByFieldId(short fieldId) {
        Field field = fieldService.get(fieldId);
        if (field.getType() != FieldType.KOL) {
            throw new InvalidException("Invalid field type");
        }
        return repo.findAllByField(field)
                .stream().map(kol -> mapper.map(kol, KolDto.class)).toList();
    }

    @Override
    public Kol getById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Kol ID not found: " + id));
    }

    @Override
    public Kol getByUser(User user) {
        return repo.findByUser(user)
                .orElseThrow(() -> new NotFoundException("Kol user ID not found: " + user.getId()));
    }

    @Override
    public Kol getByPrincipal(Principal principal) {
        User user = userService.getByPrincipal(principal);
        return getByUser(user);
    }

    @Override
    public ApiResponse getDetailsById(int id) {
        Kol kol = getById(id);
        KolDto dto = mapper.map(kol, KolDto.class);
        List<String> images = kol.getImages().stream().map(Image::getName).toList();

        return new ApiResponse(null, new HashMap<>() {{
            put("kol", dto);
            put("images", images);
        }}, null);
    }

    @Override
    public Map<String, Object> getProfileByPrincipal(Principal principal) {
        Kol kol = getByPrincipal(principal);
        KolProfileDto profile = mapper.map(kol, KolProfileDto.class);
        List<String> images = kol.getImages().stream().map(Image::getName).toList();

        return new HashMap<>() {{
            put("kol", profile);
            put("images", images);
        }};
    }

    @Override
    public ApiResponse updateProfileByPrincipal(Principal principal, KolProfileDto profile) {
        Kol kol = getByPrincipal(principal);

        kol.getUser().setFirstName(profile.getFirstName());
        kol.getUser().setLastName(profile.getLastName());
        kol.setPhone(profile.getPhone());
        kol.setGender(profile.getGender());
        kol.getAddress().setCity(cityService.get(profile.getCityId()));
        kol.getAddress().setDetails(profile.getAddressDetails());
        kol.setField(fieldService.get(profile.getFieldId()));
        kol.setFacebookUrl(profile.getFacebookUrl());
        kol.setInstagramUrl(profile.getInstagramUrl());
        kol.setTiktokUrl(profile.getTiktokUrl());
        kol.setYoutubeUrl(profile.getYoutubeUrl());

        repo.save(kol);

        return new ApiResponse("Update KOL successfully !!");
    }

    @Override
    public Map<String, Object> updateImages(Principal principal, List<MultipartFile> images) {
        Kol kol = getByPrincipal(principal);
        if (images == null) {
            throw new InvalidException("Images is null");
        }
        List<String> imageList = new ArrayList<>();
        images.forEach(image -> {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
            imageService.save(new Image(fileName, kol));
            String uploadDir = imagePath;
            FileUtils.saveImage(uploadDir, fileName, image);
            imageList.add(fileName);
        });
        return new HashMap<>() {{
            put("images", imageList);
        }};
    }

    @Override
    public ApiResponse deleteById(int id) {
        repo.deleteById(id);
        return new ApiResponse("Delete KOL successfully !!");
    }

}
