package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String secret = "secret-key-secret-key-secret-key";
    private final long expiration = 86400000; // 1 day

    private final Key key = Keys.hmacShaKeyFor(secret.getBytes());

    // ✅ DEFAULT CONSTRUCTOR (tests expect this)
    public JwtTokenProvider() {
    }

    // ✅ USED BY TESTS (String, int)
    public JwtTokenProvider(String secret, int expiration) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    // ✅ GENERATE TOKEN
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ THIS IS WHAT YOUR FILTER NEEDS (FIXES ERROR)
    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    // ✅ USED BY TESTS
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
