package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.message.ChatDto;
import com.dtu.kolgo.exception.InvalidException;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.Chat;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.repository.ChatRepository;
import com.dtu.kolgo.service.ChatService;
import com.dtu.kolgo.service.UserService;
import com.dtu.kolgo.util.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository repo;
    private final UserService userService;
    private final ModelMapper mapper;

    @Override
    public Chat save(Chat chat) {
        return repo.save(chat);
    }

    @Override
    public ChatDto save(Principal principal, ChatDto chatDto) {
        System.out.println(chatDto);
        List<User> users = new ArrayList<>() {{
            chatDto.getUserIds().forEach(id -> {
                this.add(userService.getById(id));
            });
        }};
        if (repo.existsByUsersContains(users.get(0)) && repo.existsByUsersContains(users.get(1))) {
            throw new InvalidException("Chat existed");
        }
        User user = userService.getByPrincipal(principal);
        Chat chat = repo.save(new Chat(
                chatDto.getType(),
                DateTimeUtils.convertToLocalDateTime(chatDto.getTimestamp()),
                user,
                users,
                new ArrayList<>()
        ));

        return mapper.map(chat, ChatDto.class);
    }

    @Override
    public Chat getById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Chat ID not found: " + id));
    }

    @Override
    public List<ChatDto> getDtosByPrincipal(Principal principal) {
        User user = userService.getByPrincipal(principal);
        return repo.findAllByUsersContains(user).stream().map(chat -> mapper.map(chat, ChatDto.class)).toList();
    }

    @Override
    public ChatDto getDtoById(int id) {
        Chat chat = getById(id);
        return mapper.map(chat, ChatDto.class);
    }

}
