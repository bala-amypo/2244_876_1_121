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

    private String secret = "secret-key-secret-key-secret-key";
    private long expiration = 86400000; // 1 day

    // ❌ NOT final anymore
    private Key key;

    // ✅ DEFAULT CONSTRUCTOR (tests expect this)
    public JwtTokenProvider() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    // ✅ CONSTRUCTOR USED BY TESTS
    public JwtTokenProvider(String secret, int expiration) {
        this.secret = secret;
        this.expiration = expiration;
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

    // ✅ REQUIRED BY JwtAuthenticationFilter
    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    // ✅ REQUIRED BY TESTS
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
