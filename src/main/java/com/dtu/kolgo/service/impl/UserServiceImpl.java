package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.dto.booking.BookingDto;
import com.dtu.kolgo.dto.payment.PaymentDto;
import com.dtu.kolgo.dto.user.PasswordUpdateDto;
import com.dtu.kolgo.dto.user.UserDto;
import com.dtu.kolgo.enums.BookingStatus;
import com.dtu.kolgo.exception.ExistsException;
import com.dtu.kolgo.exception.InvalidException;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.Booking;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.repository.UserRepository;
import com.dtu.kolgo.service.BookingService;
import com.dtu.kolgo.service.UserService;
import com.dtu.kolgo.util.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    @Value("${file.image-path}")
    private String imagePath;

    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;
    private final BookingService bookingService;

    @Override
    public ApiResponse save(User user) {
        repo.save(user);
        return new ApiResponse("Saved user successfully");
    }

    @Override
    public List<UserDto> getDtos() {
        return repo.findAll().stream()
                .map(user -> mapper.map(user, UserDto.class))
                .toList();
    }

    @Override
    public User getById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("User ID not found: " + id));
    }

    @Override
    public UserDto getDtoById(int id) {
        return mapper.map(getById(id), UserDto.class);
    }

    @Override
    public User getByEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Email not found: " + email));
    }

    @Override
    public User getByPrincipal(Principal principal) {
        return getByEmail(principal.getName());
    }

    @Override
    public ApiResponse updateById(int id, UserDto dto) {
        User user = getById(id);
        return updateByUser(user, dto);
    }

    @Override
    public ApiResponse updateByUser(User user, UserDto dto) {
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        repo.save(user);

        return new ApiResponse("Updated user successfully");
    }

    @Override
    public Map<String, Object> updateAvatar(Principal principal, MultipartFile avatar) {
        User user = getByPrincipal(principal);
        if (avatar == null) {
            throw new InvalidException("Avatar is null");
        }
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(avatar.getOriginalFilename()));
        user.setAvatar(fileName);
        repo.save(user);
        String uploadDir = imagePath;
        FileUtils.saveImage(uploadDir, fileName, avatar);
        return new HashMap<>() {{
            put("avatar", fileName);
        }};
    }

    @Override
    public ApiResponse updateEmail(Principal principal, String email) {
        User user = getByPrincipal(principal);
        if (email == null || email.length() == 0) {
            throw new InvalidException("Invalid email");
        }
        if (repo.existsByEmail(email)) {
            throw new ExistsException("Email already in use: " + email);
        }
        user.setEmail(email);
        repo.save(user);
        return new ApiResponse("Update email successfully");
    }

    @Override
    public ApiResponse updatePassword(Principal principal, PasswordUpdateDto request) {
        User user = getByPrincipal(principal);

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new InvalidException("Incorrect password");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        repo.save(user);

        return new ApiResponse("Updated password successfully");
    }

    @Override
    public ApiResponse deleteById(int userId) {
        repo.deleteById(userId);
        return new ApiResponse("Deleted user successfully");
    }

    @Override
    public List<BookingDto> getBookingsByPrincipal(Principal principal) {
        User user = getByPrincipal(principal);
        return user.getBookings().stream()
                .map(booking -> mapper.map(booking, BookingDto.class))
                .toList();
    }

    @Override
    public List<PaymentDto> getPaymentsByPrincipal(Principal principal) {
        User user = getByPrincipal(principal);
        return user.getPayments().stream()
                .map(payment -> mapper.map(payment, PaymentDto.class))
                .toList();
    }

    @Override
    public BookingDto getBookingByPrincipal(Principal principal, int bookingId) {
        User user = getByPrincipal(principal);
        Booking booking = bookingService.getById(bookingId);

        bookingService.validateBookingUser(user, bookingId);

        return mapper.map(booking, BookingDto.class);
    }

    @Override
    public BookingDto updateBookingStatus(Principal principal, int bookingId, BookingStatus status) {
        User user = getByPrincipal(principal);

        bookingService.validateBookingUser(user, bookingId);
        Booking booking = bookingService.updateStatus(bookingId, status);

        return mapper.map(booking, BookingDto.class);
    }

}
