package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.message.NotificationDto;
import com.dtu.kolgo.exception.InvalidException;
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
                dto.getTitle(),
                dto.getContent(),
                dto.getStatus(),
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

}
