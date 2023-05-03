package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.dto.BookingDto;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.Booking;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.repository.BookingRepository;
import com.dtu.kolgo.service.BookingService;
import com.dtu.kolgo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repo;
    private final UserService userService;
    private final ModelMapper mapper;

    @Override
    public ApiResponse save(Booking booking) {
        repo.save(booking);
        return new ApiResponse("Saved booking successfully");
    }

    @Override
    public List<BookingDto> getAllDto() {
        return repo.findAll()
                .stream()
                .map(booking -> mapper.map(booking, BookingDto.class))
                .toList();
    }

    @Override
    public List<BookingDto> getAllDtoByUserId(int userId) {
        User user = userService.get(userId);
//        String userRole = userService.getRole(userId);
        List<BookingDto> bookings = new ArrayList<>();
//        if (user.getRole().equals(Role.KOL)) {
//            bookings = mapEntitiesToDtos(kolService.getByUser(user).getBookings());
//        } else if (user.getRole().equals(Role.ENTERPRISE)) {
//            bookings = mapEntitiesToDtos(entService.get(user).getBookings());
//        }
        return null;
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

    @Override
    public ApiResponse updateById(int id, BookingDto request) {
        LocalDateTime date = LocalDateTime.parse(request.getDate(), DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        Booking booking = getById(id);
        booking.setDate(date);
        repo.save(booking);

        return new ApiResponse("Updated Booking successfully");
    }

}
