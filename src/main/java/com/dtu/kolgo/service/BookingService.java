package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.BookingDto;
import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.model.Booking;

import java.util.List;

public interface BookingService {

    ApiResponse save(Booking booking);

    List<BookingDto> getAllDto();

    List<BookingDto> getAllDtoByUserId(int userId);

    Booking getById(int id);

    BookingDto getDtoById(int id);

    ApiResponse updateById(int id, BookingDto request);

}
