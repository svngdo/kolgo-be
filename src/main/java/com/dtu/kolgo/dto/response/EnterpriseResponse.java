package com.dtu.kolgo.dto.response;

import com.dtu.kolgo.model.Booking;
import com.dtu.kolgo.model.Campaign;
import com.dtu.kolgo.model.Feedback;
import com.dtu.kolgo.model.Payment;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "enterpriseId",
        "firstName",
        "lastName",
        "email",
        "phoneNumber",
        "avatar",
        "name",
        "enterpriseFieldId",
        "taxIdentificationNumber",
        "buildingNumber",
        "streetName",
        "ward",
        "district",
        "cityId",
        "bookings",
        "payments",
        "campaigns",
        "feedbacks",
        "userId"
})
public class EnterpriseResponse {

    private Integer userId;
    private Integer enterpriseId;
    private String firstName;
    private String lastName;
    private String avatar;
    private String email;
    private String phoneNumber;
    private String name;
    private Short enterpriseFieldId;
    private String taxIdentificationNumber;
    private String buildingNumber;
    private String streetName;
    private String ward;
    private String district;
    private Short cityId;
    private List<Booking> bookings;
    private List<Payment> payments;
    private List<Campaign> campaigns;
    private List<Feedback> feedbacks;

}
