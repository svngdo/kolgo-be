package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.booking.BookingDto;
import com.dtu.kolgo.model.Booking;
import com.dtu.kolgo.model.Kol;
import com.dtu.kolgo.model.User;

import java.util.List;

public interface BookingService {

    Booking save(Booking booking);

    List<BookingDto> getDtosByKol(Kol kol);

    List<Booking> getAllByKolUser(User user);

    Booking getById(int id);

    Booking getByTxnRef(String txnRef);

    BookingDto getDtoById(int id);

}
