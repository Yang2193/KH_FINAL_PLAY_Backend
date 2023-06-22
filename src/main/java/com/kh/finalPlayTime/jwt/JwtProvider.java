package com.kh.finalPlayTime.jwt;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtProvider {
    @Value("${springboot.jwt.secret}")
    private String secretKey;

    public String createToken(String subject) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + Duration.ofDays(1).toMillis());
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("SERVER")
                .setIssuedAt(now)
                .setExpiration(expiration)
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .compact();
    }
}
