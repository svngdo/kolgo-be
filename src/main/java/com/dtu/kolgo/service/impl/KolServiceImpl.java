package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.dto.CampaignDto;
import com.dtu.kolgo.dto.booking.BookingDto;
import com.dtu.kolgo.dto.kol.KolDetailsDto;
import com.dtu.kolgo.dto.kol.KolDto;
import com.dtu.kolgo.dto.kol.KolProfileDto;
import com.dtu.kolgo.enums.BookingStatus;
import com.dtu.kolgo.exception.InvalidException;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.*;
import com.dtu.kolgo.repository.KolRepository;
import com.dtu.kolgo.service.*;
import com.dtu.kolgo.util.DateTimeUtils;
import com.dtu.kolgo.util.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.time.LocalDateTime;
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
    private final BookingService bookingService;

    private Kol getById(int id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("KOL ID not found: " + id));
    }

    private Kol getByUser(User user) {
        return repo.findByUser(user).orElseThrow(() -> new NotFoundException("User ID not found: " + user.getId()));
    }

    private Kol getByPrincipal(Principal principal) {
        User user = userService.getByPrincipal(principal);
        return getByUser(user);
    }

    @Override
    public void save(Kol kol) {
        repo.save(kol);
    }

    @Override
    public List<KolDto> getDtos() {
        return repo.findAll().stream().map(kol -> mapper.map(kol, KolDto.class)).toList();
    }

    @Override
    public List<KolDto> getDtosByFieldIds(List<Short> fieldIds) {
        List<Field> fields = fieldIds.stream().map(fieldService::getById).toList();

        return repo.findAllByFieldsIn(fields)
                .stream().map(kol -> mapper.map(kol, KolDto.class)).toList();
    }

    @Override
    public KolDetailsDto getDetailsById(int id) {
        Kol kol = getById(id);
        KolDto kolDto = mapper.map(kol, KolDto.class);
        List<String> images = kol.getImages().stream().map(Image::getName).toList();
        List<BookingDto> bookings = kol.getBookings().stream().map(booking -> mapper.map(booking, BookingDto.class)).toList();
        List<CampaignDto> campaigns = kol.getCampaigns().stream().map(campaign -> mapper.map(campaign, CampaignDto.class)).toList();

        return new KolDetailsDto(kolDto, images, bookings, campaigns);
    }

    @Override
    public Map<String, Object> getProfileByPrincipal(Principal principal) {
        Kol kol = getByPrincipal(principal);
        KolProfileDto profile = mapper.map(kol, KolProfileDto.class);
        profile.setFieldIds(kol.getFields().stream().map(BaseShort::getId).toList());
        List<String> images = kol.getImages().stream().map(Image::getName).toList();

        return new HashMap<>() {{
            put("kol", profile);
            put("images", images);
        }};
    }

    @Override
    public ApiResponse updateProfileByPrincipal(Principal principal, KolProfileDto profile) {
        Kol kol = getByPrincipal(principal);

        if (kol.getAddress() == null) {
            kol.setAddress(new Address());
        }

        kol.getUser().setFirstName(profile.getFirstName());
        kol.getUser().setLastName(profile.getLastName());
        kol.setPhone(profile.getPhone());
        kol.setGender(profile.getGender());
        kol.getAddress().setCity(cityService.get(profile.getCityId()));
        kol.getAddress().setDetails(profile.getAddressDetails());
        kol.setFields(new ArrayList<>() {{
            profile.getFieldIds().forEach(id -> this.add(fieldService.getById(id)));
        }});
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
    public List<BookingDto> getBookingsById(int id) {
        Kol kol = repo.findById(id).orElseThrow(() -> new NotFoundException("Kol ID not found: " + id));
        return bookingService.getDtosByKol(kol);
    }

    @Override
    public BookingDto createBooking(Principal principal, int id, BookingDto bookingDto) {
        System.out.println(bookingDto);
        User user = userService.getByPrincipal(principal);
        Kol kol = getById(id);

        if (kol.getUser().equals(user)) {
            throw new InvalidException("You cannot book yourself");
        }

        LocalDateTime timestamp = DateTimeUtils.convertToLocalDateTime(bookingDto.getTimestamp());

        Booking booking = bookingService.save(new Booking(
                bookingDto.getPostPrice(),
                bookingDto.getPostNumber(),
                bookingDto.getVideoPrice(),
                bookingDto.getVideoNumber(),
                bookingDto.getTotalPrice(),
                bookingDto.getDescription(),
                timestamp,
                BookingStatus.PENDING,
                user,
                kol,
                null,
                null
        ));
        return mapper.map(booking, BookingDto.class);
    }

}
