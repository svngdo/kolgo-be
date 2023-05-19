package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.message.NotificationDto;
import com.dtu.kolgo.enums.NotificationStatus;
import com.dtu.kolgo.exception.AccessDeniedException;
import com.dtu.kolgo.exception.InvalidException;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.Notification;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.repository.NotificationRepository;
import com.dtu.kolgo.service.NotificationService;
import com.dtu.kolgo.service.UserService;
import com.dtu.kolgo.util.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repo;
    private final UserService userService;
    private final ModelMapper mapper;

    @Override
    public Notification save(NotificationDto dto) {
        if (dto.getUser().getId() == null) throw new InvalidException("User ID is null");

        User user = userService.getById(dto.getUser().getId());
        return repo.save(new Notification(
                dto.getType(),
                dto.getKolId(),
                dto.getBookingId(),
                dto.getContent(),
                NotificationStatus.UNREAD,
                DateTimeUtils.convertToLocalDateTime(dto.getTimestamp()),
                user
        ));
    }

    @Override
    public List<NotificationDto> getAllByPrincipal(Principal principal) {
        User user = userService.getByPrincipal(principal);
        return repo.findAllByUser(user).stream()
                .map(notification -> mapper.map(notification, NotificationDto.class))
                .toList();
    }

    @Override
    public Notification getById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Notification not found with ID: " + id));
    }

    @Override
    public NotificationDto updateStatus(Principal principal, int id, NotificationStatus status) {
        User user = userService.getByPrincipal(principal);
        Notification notification = getById(id);
        if (user.getNotifications() == null || !user.getNotifications().contains(notification))
            throw new AccessDeniedException();

        notification.setStatus(status);
        repo.save(notification);
        return mapper.map(notification, NotificationDto.class);
    }

}
