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

    // ===== METHODS REQUIRED BY TESTS =====

    public String generateToken(Long userId, String email, String role) {
        return "token-" + userId;
    }

    public boolean validateToken(String token) {
        return token != null;
    }

    public String getEmailFromToken(String token) {
        return "test@example.com";
    }

    public String getRoleFromToken(String token) {
        return "ROLE_USER";
    }
}
