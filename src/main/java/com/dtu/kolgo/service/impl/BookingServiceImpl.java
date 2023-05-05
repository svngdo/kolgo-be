package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.dto.booking.BookingCreateDto;
import com.dtu.kolgo.dto.booking.BookingDto;
import com.dtu.kolgo.enums.DateTimeFormat;
import com.dtu.kolgo.enums.Role;
import com.dtu.kolgo.exception.ExistsException;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.Booking;
import com.dtu.kolgo.model.Kol;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.repository.BookingRepository;
import com.dtu.kolgo.service.BookingService;
import com.dtu.kolgo.service.KolService;
import com.dtu.kolgo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
                LocalDateTime.parse(dto.getDate(), DateTimeFormatter.ofPattern(DateTimeFormat.getSimple())),
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
    public List<BookingDto> getAllDtoByUserId(int userId) {
        User user = userService.getById(userId);
        List<Booking> bookings = user.getBookings();

        if (user.getRole() == Role.KOL) {
            bookings.addAll(repo.findAllByKol(kolService.getByUser(user)));
        }
        return bookings.stream()
                .map(booking -> mapper.map(booking, BookingDto.class))
                .toList();
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
        BookingDto bookingDto = mapper.map(booking, BookingDto.class);
//        bookingDto.setDate(booking.getDate().format(DateTimeFormatter.ofPattern(DateTimeFormat.getSimple())));
        return bookingDto;
    }

    @Override
    public ApiResponse updateById(int id, BookingDto request) {
//        LocalDateTime date = LocalDateTime.parse(request.getDate(), DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
//
//        Booking booking = getById(id);
//        booking.setDate();
//        repo.save(booking);

        return new ApiResponse("Updated Booking successfully");
    }

}
