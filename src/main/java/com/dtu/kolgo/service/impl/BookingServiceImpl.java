package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.booking.BookingDto;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.Booking;
import com.dtu.kolgo.model.Kol;
import com.dtu.kolgo.repository.BookingRepository;
import com.dtu.kolgo.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repo;
    private final ModelMapper mapper;

    @Override
    public Booking save(Booking booking) {
        return repo.save(booking);
    }

    @Override
    public List<BookingDto> getDtosByKol(Kol kol) {
        return repo.findAllByKol(kol).stream()
                .map(booking -> mapper.map(booking, BookingDto.class)).collect(Collectors.toList());
    }

    @Override
    public Booking getById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found Booking with ID: " + id));
    }

    @Override
    public Booking getByTxnRef(String txnRef) {
        return repo.findByTxnRef(txnRef);
    }

    @Override
    public BookingDto getDtoById(int id) {
        Booking booking = getById(id);
        return mapper.map(booking, BookingDto.class);
    }

}
