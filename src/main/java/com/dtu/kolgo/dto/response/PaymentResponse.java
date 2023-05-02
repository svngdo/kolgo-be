package com.dtu.kolgo.dto.response;

import com.dtu.kolgo.enums.BankCode;
import com.dtu.kolgo.enums.PaymentMethod;
import com.dtu.kolgo.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentResponse {

    private int id;
    private PaymentMethod method;
    private String txnRef;
    private String amount;
    private String description;
    private String txnNo;
    private BankCode bankCode;
    private String date;
    private PaymentStatus status;
    private int userId;

}
