package com.example.demo.dto;

public class AuthRequest {

    private String email;
    private String password;

    public AuthRequest() {}

    public String getEmail() { return email; }
    public String getPassword() { return password; }

    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }

    // REQUIRED BY TESTS
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final AuthRequest a = new AuthRequest();

        public Builder email(String email) { a.setEmail(email); return this; }
        public Builder password(String password) { a.setPassword(password); return this; }

        public AuthRequest build() { return a; }
    }
}
