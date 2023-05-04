package com.dtu.kolgo.dto.kol;

import com.dtu.kolgo.dto.BookingDto;
import com.dtu.kolgo.dto.FeedbackDto;
import com.dtu.kolgo.dto.PaymentDto;
import com.dtu.kolgo.dto.UserDto;
import com.dtu.kolgo.enums.Gender;
import com.dtu.kolgo.model.Address;
import com.dtu.kolgo.model.Field;
import lombok.Data;

import java.util.List;

@Data
public class KolDetailsDto {

    private UserDto user;
    private String phone;
    private Gender gender;
    private Address address;
    private Field field;
    private String facebookUrl;
    private String instagramUrl;
    private String tiktokUrl;
    private String youtubeUrl;
    private List<String> images;
    private List<BookingDto> bookings;
    private List<PaymentDto> payments;
    private List<FeedbackDto> feedbacks;

}
