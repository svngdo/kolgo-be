package com.dtu.kolgo.dto.payment;

import com.dtu.kolgo.dto.user.UserDto;
import com.dtu.kolgo.enums.PaymentMethod;
import lombok.Data;

@Data
public class VnPayDto {

    private PaymentMethod method;
    private UserDto userDto;
    private String vnp_Amount;
    private String vnp_BankCode;
    private String vnp_BankTranNo;
    private String vnp_CardType;
    private String vnp_OrderInfo;
    private String vnp_PayDate;
    private String vnp_ResponseCode;
    private String vnp_TmnCode;
    private String vnp_TransactionNo;
    private String vnp_TransactionStatus;
    private String vnp_TxnRef;

}
