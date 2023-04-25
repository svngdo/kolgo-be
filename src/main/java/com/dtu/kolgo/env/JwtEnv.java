package com.dtu.kolgo.env;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtEnv {

    public static String SECRET;
    public static long ACCESS_EXPIRATION;
    public static long REFRESH_EXPIRATION;
    public static long DAY_EXPIRATION;
    public static long RESET_PASSWORD_EXPIRATION;
    public static long VERIFY_ACCOUNT_EXPIRATION;

    @Value("${JWT_SECRET}")
    private void setSecret(String secret) {
        SECRET = secret;
    }

    @Value("${TOKEN_ACCESS_EXPIRATION}")
    private void setAccessExpiration(long expiration) {
        ACCESS_EXPIRATION = expiration;
    }

    @Value("${TOKEN_REFRESH_EXPIRATION}")
    private void setRefreshExpiration(long expiration) {
        REFRESH_EXPIRATION = expiration;
    }

    @Value("${TOKEN_DAY_EXPIRATION}")
    private void setDayExpiration(long expiration) {
        DAY_EXPIRATION = expiration;
    }

    @Value("${TOKEN_RESET_PASSWORD_EXPIRATION}")
    private void setResetPasswordExpiration(long expiration) {
        RESET_PASSWORD_EXPIRATION = expiration;
    }

    @Value("${TOKEN_VERIFY_ACCOUNT_EXPIRATION}")
    private void setVerifyAccountExpiration(long expiration) {
        VERIFY_ACCOUNT_EXPIRATION = expiration;
    }

}
