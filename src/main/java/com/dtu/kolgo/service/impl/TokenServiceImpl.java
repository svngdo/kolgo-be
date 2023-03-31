package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.repository.TokenRepository;
import com.dtu.kolgo.security.JwtProvider;
import com.dtu.kolgo.service.TokenService;
import com.dtu.kolgo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenRepository repo;
    private final UserService userService;
    private final JwtProvider jwtProvider;

    @Override
    public void revoke(String value) {
        if (repo.existsByValue(value)) {
            repo.deleteByValue(value);
            System.out.println("Delete single token: " + value);
        } else {
            int userId = jwtProvider.extractUserId(value);
            User user = userService.fetch(userId);
            repo.deleteAllByUser(user);
            System.out.println("Delete all tokens of user " + user.getUsername());
            throw new NotFoundException("Token not found: " + value);
        }
    }

}
