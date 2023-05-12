package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.booking.BookingDto;
import com.dtu.kolgo.enums.BookingStatus;
import com.dtu.kolgo.model.Booking;
import com.dtu.kolgo.model.Kol;
import com.dtu.kolgo.model.User;

import java.util.List;

public interface BookingService {

    Booking save(Booking booking);

    List<BookingDto> getDtosByKol(Kol kol);

    Booking getById(int id);

    BookingDto getDtoById(int id);

    Booking updateStatus(int id, BookingStatus status);

    void validateBookingUser(User user, int bookingId);

}
