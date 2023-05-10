package com.dtu.kolgo.dto.kol;

import com.dtu.kolgo.dto.booking.BookingDto;
import com.dtu.kolgo.dto.FeedbackDto;
import com.dtu.kolgo.dto.payment.PaymentDto;
import com.dtu.kolgo.dto.user.UserDto;
import com.dtu.kolgo.enums.Gender;
import com.dtu.kolgo.model.Address;
import com.dtu.kolgo.model.Field;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class KolDetailsDto {

    private UserDto user;
    private Integer id;
    private String phone;
    private Gender gender;
    private Address address;
    private Field field;
    private BigDecimal postPrice;
    private BigDecimal videoPrice;
    private String facebookUrl;
    private String instagramUrl;
    private String tiktokUrl;
    private String youtubeUrl;
    private List<String> images;
    private List<BookingDto> bookings;
    private List<PaymentDto> payments;
    private List<FeedbackDto> feedbacks;

}
