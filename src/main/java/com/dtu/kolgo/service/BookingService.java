package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.booking.BookingCreateDto;
import com.dtu.kolgo.dto.booking.BookingDto;
import com.dtu.kolgo.model.Booking;

import java.security.Principal;
import java.util.List;

public interface BookingService {

    BookingDto create(Principal principal, BookingCreateDto dto);

    List<BookingDto> getAllDtoByPrincipal(Principal principal);

    Booking getById(int id);

    BookingDto getDtoById(int id);

}
