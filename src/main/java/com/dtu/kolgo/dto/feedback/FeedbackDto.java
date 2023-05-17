package com.dtu.kolgo.dto.feedback;

import com.dtu.kolgo.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeedbackDto {

    private Integer id;
    private Short rating;
    private String comment;
    private UserDto user;

}
