package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.request.KolUpdateRequest;
import com.dtu.kolgo.dto.response.KolResponse;
import com.dtu.kolgo.dto.response.WebResponse;
import com.dtu.kolgo.repository.KolRepository;
import com.dtu.kolgo.repository.UserRepository;
import com.dtu.kolgo.security.JwtProvider;
import com.dtu.kolgo.service.CityService;
import com.dtu.kolgo.service.KolService;
import com.dtu.kolgo.service.MailService;
import com.dtu.kolgo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KolServiceImpl implements KolService {

    private final KolRepository repo;
    private final UserRepository userRepo;
    private final UserService userService;
    private final CityService cityService;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public KolResponse update(KolUpdateRequest request) {
//        User user = userService.save(User.builder()
//                .username(UUID.randomUUID().toString())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .phoneNumber(request.getPhoneNumber())
//                .roles(Collections.singletonList(Role.KOL))
//                .build());
//        Kol kol = Kol.builder()
//                .firstName(request.getFirstName())
//                .lastName(request.getLastName())
//                .gender(request.getGender() == null ? null : Gender.valueOf(request.getGender().toUpperCase()))
//                .speciality(request.getSpeciality())
//                .city(request.getCity() == null ? null : cityService.get(request.getCity()))
//                .facebookUrl(request.getFacebookUrl())
//                .instagramUrl(request.getInstagramUrl())
//                .tiktokUrl(request.getTiktokUrl())
//                .youtubeUrl(request.getYoutubeUrl())
//                .user(user)
//                .build();
//        return KolResponse.builder()
//                .id(user.getId())
//                .firstName(kol.getFirstName())
//                .lastName(kol.getLastName())
//                .gender(kol.getGender() == null ? null : kol.getGender().toString())
//                .phoneNumber(user.getPhoneNumber())
//                .speciality(kol.getSpeciality())
//                .city(kol.getGender() == null ? null : kol.getCity().toString())
//                .facebookUrl(kol.getFacebookUrl())
//                .instagramUrl(kol.getInstagramUrl())
//                .tiktokUrl(kol.getTiktokUrl())
//                .youtubeUrl(kol.getYoutubeUrl())
//                .roles(user.getRoles())
//                .build();
        return null;
    }

    @Override
    public List<KolResponse> fetchAll() {
        return null;
    }

    @Override
    public KolResponse fetch(int id) {
        return null;
    }

    @Override
    public KolResponse update(int id, KolUpdateRequest request) {
        return null;
    }

    @Override
    public WebResponse delete(int id) {
        return null;
    }

}
