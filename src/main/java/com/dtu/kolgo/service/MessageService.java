package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.message.MessageDto;
import com.dtu.kolgo.dto.message.NotificationDto;

public interface MessageService {

    void handlePublicMessage(MessageDto dto);

    void handlePrivateMessage(MessageDto dto);

    void handlePrivateNotification(NotificationDto dto);

}
