package com.dtu.kolgo.dto;

import com.dtu.kolgo.dto.kol.KolDto;
import com.dtu.kolgo.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingDto {

    private Integer id;
    private String date;
    private BookingStatus status;
    UserDto user;
    KolDto kol;

}
