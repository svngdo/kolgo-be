package com.dtu.kolgo.security;

import com.dtu.kolgo.exception.CustomJwtException;
import com.dtu.kolgo.exception.InvalidException;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.util.constant.GrantType;
import com.dtu.kolgo.util.constant.JwtKey;
import com.dtu.kolgo.util.env.Jwt;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
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
        String authHeader = request.getHeader("Authorization");
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer")) {
            return authHeader.substring(7);
        }
        return null;
    }

    private Key getSigningKey() {
        byte[] keyBytes = Jwt.SECRET.getBytes(StandardCharsets.UTF_8);
        byte[] key64UrlBytes = Encoders.BASE64URL.encode(keyBytes).getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(key64UrlBytes);
    }

    public String generateToken(String sub, long ttlMillis, GrantType grantType, Map<String, Object> extraClaims) {
        long nowMillis = System.currentTimeMillis();
        Date iat = new Date(nowMillis);
        Date exp = new Date(iat.getTime() + ttlMillis);
        return Jwts.builder()
                .setSubject(sub)
                .setExpiration(exp)
                .setIssuedAt(iat)
                .claim(JwtKey.GRANT_TYPE.toString(), grantType)
                .addClaims(extraClaims)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }

    public String generateToken(String sub, long ttlMillis, GrantType grantType) {
        return generateToken(sub, ttlMillis, grantType, new HashMap<>());
    }

    public String generateAccessToken(User user) {
        return generateToken(
                String.valueOf(user.getId()),
                Jwt.ACCESS_TOKEN_EXPIRATION,
                GrantType.ACCESS_TOKEN
        );
    }

    public String generateRefreshToken(User user) {
        return generateToken(
                String.valueOf(user.getId()),
                Jwt.REFRESH_TOKEN_EXPIRATION,
                GrantType.REFRESH_TOKEN
        );
    }

    public String generateResetPasswordToken(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put(JwtKey.PASSWORD.toString(), user.getPassword());
        return generateToken(
                String.valueOf(user.getId()),
                Jwt.RESET_PASSWORD_TOKEN_EXPIRATION,
                GrantType.RESET_PASSWORD_TOKEN,
                extraClaims
        );
    }

    public String generateVerifyAccountToken(
            String firstName, String lastName, String email, String password
    ) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put(JwtKey.FIRST_NAME.toString(), firstName);
        extraClaims.put(JwtKey.LAST_NAME.toString(), lastName);
        extraClaims.put(JwtKey.EMAIL.toString(), email);
        extraClaims.put(JwtKey.PASSWORD.toString(), password);
        return generateToken(
                "0",
                Jwt.VERIFY_ACCOUNT_TOKEN_EXPIRATION,
                GrantType.VERIFY_ACCOUNT_TOKEN,
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
        return claims.get(JwtKey.EMAIL.toString(), String.class);
    }

    public String extractPassword(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get(JwtKey.PASSWORD.toString(), String.class);
    }

    public String extractFirstName(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get(JwtKey.FIRST_NAME.toString(), String.class);
    }

    public String extractLastName(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get(JwtKey.LAST_NAME.toString(), String.class);
    }

    public String extractGrantType(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get(JwtKey.GRANT_TYPE.toString(), String.class);
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

    public boolean validateGrantType(String token, GrantType type) {
        String grantType = extractGrantType(token);
        if (grantType.equals(type.name())) {
            return true;
        }
        throw new InvalidException("Invalid token type");
    }

}
