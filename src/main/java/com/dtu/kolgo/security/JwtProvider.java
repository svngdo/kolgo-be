package com.dtu.kolgo.security;

import com.dtu.kolgo.exception.CustomJwtException;
import com.dtu.kolgo.exception.InvalidException;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.enums.GrantTypes;
import com.dtu.kolgo.enums.JwtKeys;
import com.dtu.kolgo.env.JwtEnv;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtProvider {

    public String resolveToken(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer")) {
            return authHeader.substring(7);
        }
        return null;
    }

    private Key getSigningKey() {
        byte[] keyBytes = JwtEnv.SECRET.getBytes(StandardCharsets.UTF_8);
        byte[] key64UrlBytes = Encoders.BASE64URL.encode(keyBytes).getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(key64UrlBytes);
    }

    public String generateToken(String sub, long ttlMillis, GrantTypes grantTypes, Map<String, Object> extraClaims) {
        long nowMillis = System.currentTimeMillis();
        Date iat = new Date(nowMillis);
        Date exp = new Date(iat.getTime() + ttlMillis);
        return Jwts.builder()
                .setSubject(sub)
                .setExpiration(exp)
                .setIssuedAt(iat)
                .claim(JwtKeys.GRANT_TYPE.toString(), grantTypes)
                .addClaims(extraClaims)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }

    public String generateToken(String sub, long ttlMillis, GrantTypes grantTypes) {
        return generateToken(sub, ttlMillis, grantTypes, new HashMap<>());
    }

    public String generateAccessToken(User user) {
        return generateToken(
                String.valueOf(user.getId()),
                JwtEnv.ACCESS_EXPIRATION,
                GrantTypes.ACCESS_TOKEN
        );
    }

    public String generateRefreshToken(User user) {
        return generateToken(
                String.valueOf(user.getId()),
                JwtEnv.REFRESH_EXPIRATION,
                GrantTypes.REFRESH_TOKEN
        );
    }

    public String generateResetPasswordToken(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put(JwtKeys.PASSWORD.toString(), user.getPassword());
        return generateToken(
                String.valueOf(user.getId()),
                JwtEnv.RESET_PASSWORD_EXPIRATION,
                GrantTypes.RESET_PASSWORD_TOKEN,
                extraClaims
        );
    }

    public String generateVerifyAccountToken(
            String firstName, String lastName, String email, String password
    ) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put(JwtKeys.FIRST_NAME.toString(), firstName);
        extraClaims.put(JwtKeys.LAST_NAME.toString(), lastName);
        extraClaims.put(JwtKeys.EMAIL.toString(), email);
        extraClaims.put(JwtKeys.PASSWORD.toString(), password);
        return generateToken(
                "0",
                JwtEnv.VERIFY_ACCOUNT_EXPIRATION,
                GrantTypes.VERIFY_ACCOUNT_TOKEN,
                extraClaims
        );
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Integer extractUserId(String token) {
        return Integer.parseInt(extractClaim(token, Claims::getSubject));
    }

    public String extractEmail(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get(JwtKeys.EMAIL.toString(), String.class);
    }

    public String extractPassword(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get(JwtKeys.PASSWORD.toString(), String.class);
    }

    public String extractFirstName(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get(JwtKeys.FIRST_NAME.toString(), String.class);
    }

    public String extractLastName(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get(JwtKeys.LAST_NAME.toString(), String.class);
    }

    public String extractGrantType(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get(JwtKeys.GRANT_TYPE.toString(), String.class);
    }

    public boolean validate(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            throw new CustomJwtException("Token expired");
        } catch (IllegalArgumentException
                 | SignatureException
                 | UnsupportedJwtException
                 | MalformedJwtException ex) {
            throw new InvalidException("Invalid token");
        }
    }

    public boolean validateGrantType(String token, GrantTypes type) {
        String grantType = extractGrantType(token);
        if (grantType.equals(type.name())) {
            return true;
        }
        throw new InvalidException("Invalid token type");
    }

}
