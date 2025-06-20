package com.example.oauth_back.common.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String secretKey; // 인코딩된 시크릿
    private final int expiration;
    private final Key SECRET_KEY; // 어떤 암호화를 쓸건지 설정된 시크릿


    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey,
                            @Value("${jwt.expiration}") int expiration) {
        this.secretKey = secretKey;
        this.expiration = expiration;
        this.SECRET_KEY = new SecretKeySpec(
                java.util.Base64.getDecoder().decode(secretKey),
                SignatureAlgorithm.HS512.getJcaName());
    }

    // Claims는 jwt 토큰의 payload부분을 의미
    public String createToken(String email, String role) {
        // payload 생성
        Claims claims = Jwts.claims().subject(email).build();
        claims.put("role", role);

        // 날짜 객체 생성
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + expiration * 60 * 1000L);

        // 토큰 빌더
        return Jwts.builder()
                .claims(claims)
                .issuedAt(currentDate)
                .expiration(expirationDate)
                .signWith(SECRET_KEY)
                .compact();
    }
}
