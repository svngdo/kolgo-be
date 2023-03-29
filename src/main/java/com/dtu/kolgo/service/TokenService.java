package com.dtu.kolgo.service;

import com.dtu.kolgo.model.Token;
import com.dtu.kolgo.model.User;

public interface TokenService {

    Token get(String value);

    Token get(User user);

}
