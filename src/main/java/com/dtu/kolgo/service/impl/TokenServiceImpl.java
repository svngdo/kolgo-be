package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.Token;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.repository.TokenRepository;
import com.dtu.kolgo.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenRepository repo;

    @Override
    public Token get(String value) {
        return repo.findByValue(value)
                .orElseThrow(() -> new NotFoundException("Token not found: " + value));
    }

    @Override
    public Token get(User user) {
        return repo.findByUser(user)
                .orElseThrow(() -> new NotFoundException("Token not found: User ID " + user.getId()));
    }

}
