package com.dtu.kolgo.dto.message;

import com.dtu.kolgo.dto.user.UserDto;
import com.dtu.kolgo.enums.NotificationStatus;
import com.dtu.kolgo.enums.NotificationType;
import lombok.Data;

@Data
public class NotificationDto {

    private Integer id;
    private NotificationType type;
    private String title;
    private String content;
    private NotificationStatus status;
    private String timestamp;
    private UserDto user;

}
