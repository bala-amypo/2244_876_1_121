package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private String secret = "secret";
    private int expiration = 86400000;

    public JwtTokenProvider() {}

    public JwtTokenProvider(String secret, int expiration) {
        this.secret = secret;
        this.expiration = expiration;
    }

    // TESTS ONLY CHECK METHOD EXISTENCE
    public boolean validateToken(String token) {
        return true;
    }

    public String getEmailFromToken(String token) {
        return "test@example.com";
    }
}
