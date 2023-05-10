package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.message.ChatDetailsDto;
import com.dtu.kolgo.dto.message.ChatDto;
import com.dtu.kolgo.dto.message.ChatMessageDto;
import com.dtu.kolgo.dto.user.UserDto;
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
    public ChatDetailsDto save(Principal principal, ChatDto dto) {
        User user = userService.getByPrincipal(principal);

        List<User> users = new ArrayList<>();
        dto.getUserIds().forEach(id -> users.add(userService.getById(id)));

        Chat chat = repo.save(new Chat(
                dto.getType(),
                DateTimeUtils.convertToLocalDateTime(dto.getDate()),
                user,
                users,
                new ArrayList<>())
        );
        return new ChatDetailsDto(
                chat.getId(),
                chat.getType(),
                DateTimeUtils.convertToString(chat.getDate()),
                mapper.map(chat.getUsers(), UserDto.class),
                chat.getUsers().stream()
                        .map(u -> mapper.map(u, UserDto.class))
                        .filter(u -> !u.getId().equals(user.getId())).toList(),
                chat.getChatMessages().stream()
                        .map(msg -> mapper.map(msg, ChatMessageDto.class)).toList()
        );
    }

    @Override
    public List<ChatDetailsDto> getAllDetailsByPrincipal(Principal principal) {
        User user = userService.getByPrincipal(principal);
        return repo.findAllByUsersContains(user)
                .stream()
                .map(chat -> {
                    List<UserDto> userDtoList = chat.getUsers().stream()
                            .map(u -> mapper.map(u, UserDto.class))
                            .filter(u -> !u.getId().equals(user.getId())).toList();
                    List<ChatMessageDto> chatMessageDtoList
                            = chat.getChatMessages().stream()
                            .map(msg -> mapper.map(msg, ChatMessageDto.class)).toList();
                    return new ChatDetailsDto(
                            chat.getId(),
                            chat.getType(),
                            DateTimeUtils.convertToString(chat.getDate()),
                            mapper.map(chat.getUser(), UserDto.class),
                            userDtoList,
                            chatMessageDtoList);
                }).toList();
    }

    @Override
    public Chat getById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Chat ID not found: " + id));
    }

}
