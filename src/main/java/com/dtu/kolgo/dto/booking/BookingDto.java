package com.dtu.kolgo.dto.booking;

import com.dtu.kolgo.dto.UserDto;
import com.dtu.kolgo.dto.kol.KolDto;
import com.dtu.kolgo.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingDto {

    private Integer id;
    private String date;
    private BigDecimal postPrice;
    private Integer postNumber;
    private BigDecimal videoPrice;
    private Integer videoNumber;
    private BigDecimal totalPrice;
    private BookingStatus status;
    UserDto user;
    KolDto kol;

}
