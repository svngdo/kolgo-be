package com.dtu.kolgo.service;

import com.dtu.kolgo.model.Token;

public interface TokenService {

    void save(Token token);

    void revoke(String value);

}
