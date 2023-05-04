package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.BookingDto;
import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.model.Booking;

import java.util.List;

public interface BookingService {

    ApiResponse save(Booking booking);

    List<BookingDto> getAllDto();

    List<BookingDto> getAllDto(int userId);

    Booking get(int id);

    BookingDto getDto(int id);

    ApiResponse update(int id, BookingDto request);

}
