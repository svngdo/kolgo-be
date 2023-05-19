package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.message.NotificationDto;
import com.dtu.kolgo.enums.NotificationStatus;
import com.dtu.kolgo.model.Notification;

import java.security.Principal;
import java.util.List;

public interface NotificationService {

    Notification save(NotificationDto dto);

    List<NotificationDto> getAllByPrincipal(Principal principal);

    Notification getById(int id);

    NotificationDto updateStatus(Principal principal, int id, NotificationStatus status);

}
