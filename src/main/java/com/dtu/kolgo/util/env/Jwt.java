package com.dtu.kolgo.util.env;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Jwt {

    public static String SECRET;
    public static long ACCESS_TOKEN_EXPIRATION;
    public static long REFRESH_TOKEN_EXPIRATION;
    public static long DB_TOKEN_EXPIRATION;
    public static long RESET_PASSWORD_TOKEN_EXPIRATION;
    public static long VERIFY_ACCOUNT_TOKEN_EXPIRATION;

    @Value("${JWT_SECRET}")
    private void setSecret(String secret) {
        SECRET = secret;
    }

    @Value("${ACCESS_TOKEN_EXPIRATION}")
    private void setAccessTokenExpiration(long expiration) {
        ACCESS_TOKEN_EXPIRATION = expiration;
    }

    @Value("${REFRESH_TOKEN_EXPIRATION}")
    private void setRefreshTokenExpiration(long expiration) {
        REFRESH_TOKEN_EXPIRATION = expiration;
    }

    @Value("${DB_TOKEN_EXPIRATION}")
    private void setDbTokenExpiration(long expiration) {
        DB_TOKEN_EXPIRATION = expiration;
    }

    @Value("${RESET_PASSWORD_TOKEN_EXPIRATION}")
    private void setResetPasswordTokenExpiration(long expiration) {
        RESET_PASSWORD_TOKEN_EXPIRATION = expiration;
    }

    @Value("${VERIFY_ACCOUNT_TOKEN_EXPIRATION}")
    private void setVerifyAccountTokenExpiration(long expiration) {
        VERIFY_ACCOUNT_TOKEN_EXPIRATION = expiration;
    }

}
