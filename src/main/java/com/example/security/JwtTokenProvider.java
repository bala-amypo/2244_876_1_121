package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    public JwtTokenProvider() {
    }

    public String generateToken(Long userId, String email, String role) {
        return userId + ":" + email;
    }

    public boolean validateToken(String token) {
        return token != null && token.contains(":");
    }

    public String getEmailFromToken(String token) {
        return token.split(":")[1];
    }

    public Long getUserIdFromToken(String token) {
        return Long.parseLong(token.split(":")[0]);
    }
}
