package com.dtu.kolgo.dto.payment;

import com.dtu.kolgo.dto.user.UserDto;
import com.dtu.kolgo.enums.BankCode;
import com.dtu.kolgo.enums.PaymentMethod;
import com.dtu.kolgo.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDto {

    private int id;
    private PaymentMethod method;
    private String txnRef;
    private String amount;
    private String description;
    private String txnNo;
    private BankCode bankCode;
    private String timestamp;
    private PaymentStatus status;
    private UserDto user;

}
