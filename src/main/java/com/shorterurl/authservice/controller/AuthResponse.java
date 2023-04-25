package com.shorterurl.authservice.controller;
import com.shorterurl.authservice.model.Role;

import java.util.Set;

public class AuthResponse {
    private String token;
    private Set<Role> roles;

    public AuthResponse(String token, Set<Role> roles) {
        this.token = token;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Set<Role> getRole() {
        return roles;
    }

    public void setRole(Set<Role> roles) {
        this.roles = roles;
    }
}
