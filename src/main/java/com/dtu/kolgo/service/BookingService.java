package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.BookingRequest;
import com.dtu.kolgo.dto.response.ApiResponse;
import com.dtu.kolgo.dto.response.BookingResponse;
import com.dtu.kolgo.model.Booking;

import java.util.List;

public interface BookingService {

    ApiResponse save(Booking booking);

    List<Booking> getAll();

    List<BookingResponse> getAllResponses();

    List<BookingResponse> getAllResponses(int userId);

    Booking get(int id);

    BookingResponse getResponse(int id);

    ApiResponse update(int id, BookingRequest request);

    ApiResponse delete(int id);

    BookingResponse mapEntityToDto(Booking booking);

    List<BookingResponse> mapEntitiesToDtos(List<Booking> bookings);

}
