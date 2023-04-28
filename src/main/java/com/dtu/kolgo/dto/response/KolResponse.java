package com.dtu.kolgo.dto.response;

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
        "id",
        "userId",
        "firstName",
        "lastName",
        "genderId",
        "email",
        "phoneNumber",
        "avatar",
        "cityId",
        "kolFieldId",
        "images",
        "facebookUrl",
        "instagramUrl",
        "tiktokUrl",
        "youtubeUrl",
        "feedbacks"
})
public class KolResponse {

    private Integer id;
    private Integer userId;
    private String avatar;
    private String firstName;
    private String lastName;
    private String email;
    private Short genderId;
    private String phoneNumber;
    private List<ImageResponse> images;
    private Short cityId;
    private Short kolFieldId;
    private String facebookUrl;
    private String instagramUrl;
    private String tiktokUrl;
    private String youtubeUrl;
    private List<FeedbackResponse> feedbacks;

}
