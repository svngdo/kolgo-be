package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.booking.BookingCreateDto;
import com.dtu.kolgo.dto.booking.BookingDto;
import com.dtu.kolgo.exception.ExistsException;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.Booking;
import com.dtu.kolgo.model.Kol;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.repository.BookingRepository;
import com.dtu.kolgo.service.BookingService;
import com.dtu.kolgo.service.KolService;
import com.dtu.kolgo.service.UserService;
import com.dtu.kolgo.util.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repo;
    private final UserService userService;
    private final ModelMapper mapper;
    private final KolService kolService;

    @Override
    public BookingDto create(Principal principal, BookingCreateDto dto) {
        User user = userService.getByPrincipal(principal);
        Kol kol = kolService.getById(dto.getKolId());

        if (repo.existsByUserAndKol(user, kol)) throw new ExistsException("Booking existed");

        Booking booking = repo.save(new Booking(
                DateTimeUtils.convertToLocalDateTime(dto.getDate()),
                dto.getPostPrice(),
                dto.getPostNumber(),
                dto.getVideoPrice(),
                dto.getVideoNumber(),
                dto.getTotalPrice(),
                dto.getStatus(),
                user,
                kol,
                null,
                new ArrayList<>()
        ));

        return mapper.map(booking, BookingDto.class);
    }

    @Override
    public List<BookingDto> getAllDtoByPrincipal(Principal principal) {
        User user = userService.getByPrincipal(principal);
        return repo.findAllByUser(user).stream()
                .map(booking -> mapper.map(booking, BookingDto.class))
                .toList();
    }

    @Override
    public Booking getById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found Booking with ID: " + id));
    }

    @Override
    public BookingDto getDtoById(int id) {
        Booking booking = getById(id);
        return mapper.map(booking, BookingDto.class);
    }

}
