package com.dtu.kolgo.dto;

import com.dtu.kolgo.dto.booking.BookingDto;
import com.dtu.kolgo.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeedbackDto {

    private int id;
    private short rating;
    private String comment;
    private UserDto sender;
    private UserDto receiver;
    private BookingDto booking;

}
