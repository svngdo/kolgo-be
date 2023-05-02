package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.request.BookingRequest;
import com.dtu.kolgo.dto.response.ApiResponse;
import com.dtu.kolgo.dto.response.BookingResponse;
import com.dtu.kolgo.dto.response.FeedbackResponse;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.Booking;
import com.dtu.kolgo.enums.Role;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.repository.BookingRepository;
import com.dtu.kolgo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repo;
    private final UserService userService;
    private final KolService kolService;
    private final EnterpriseService entService;
    private final FeedbackService feedbackService;

    @Override
    public ApiResponse save(Booking booking) {
        repo.save(booking);
        return new ApiResponse("Saved booking successfully");
    }

    @Override
    public List<Booking> getAll() {
        return repo.findAll();
    }

    @Override
    public List<BookingResponse> getAllResponses() {
        return mapEntitiesToDtos(getAll());
    }

    @Override
    public List<BookingResponse> getAllResponses(int userId) {
        User user = userService.get(userId);
//        String userRole = userService.getRole(userId);
        List<BookingResponse> bookings = new ArrayList<>();
        if (user.getRole().equals(Role.KOL)) {
            bookings = mapEntitiesToDtos(kolService.get(user).getBookings());
        } else if (user.getRole().equals(Role.ENTERPRISE)) {
            bookings = mapEntitiesToDtos(entService.get(user).getBookings());
        }
        return bookings;
    }

    @Override
    public Booking get(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found Booking with ID: " + id));
    }

    @Override
    public BookingResponse getResponse(int id) {
        return mapEntityToDto(get(id));
    }

    @Override
    public ApiResponse update(int id, BookingRequest request) {
        LocalDateTime date = LocalDateTime.parse(request.getDate(), DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        Booking booking = get(id);
        booking.setDate(date);
        repo.save(booking);

        return new ApiResponse("Updated Booking successfully");
    }

    @Override
    public ApiResponse delete(int id) {
        repo.deleteById(id);
        return new ApiResponse("Deleted Feedback successfully");
    }

    @Override
    public BookingResponse mapEntityToDto(Booking booking) {
        List<FeedbackResponse> feedbacks = new ArrayList<>();
        if (booking.getFeedbacks() != null) {
            feedbacks = booking.getFeedbacks().stream()
                    .map(feedbackService::mapEntityToDto)
                    .collect(Collectors.toList());
        }
        return BookingResponse.builder()
                .id(booking.getId())
                .date(booking.getDate().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))
                .entId(booking.getEnterprise().getId())
                .entFirstName(booking.getEnterprise().getUser().getFirstName())
                .entLastName(booking.getEnterprise().getUser().getLastName())
                .entName(booking.getEnterprise().getName())
                .kolId(booking.getKol().getId())
                .kolFirstName(booking.getKol().getUser().getFirstName())
                .kolLastName(booking.getKol().getUser().getLastName())
                .paymentAmount(booking.getPayment().getAmount().toString())
                .paymentStatus(booking.getPayment().getStatus())
                .feedbacks(feedbacks)
                .build();
    }

    @Override
    public List<BookingResponse> mapEntitiesToDtos(List<Booking> bookings) {
        return bookings.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

}
