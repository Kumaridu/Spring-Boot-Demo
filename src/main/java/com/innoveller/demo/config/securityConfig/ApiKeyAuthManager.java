package com.innoveller.demo.config.securityConfig;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class ApiKeyAuthManager implements AuthenticationManager {
    private final String  token = "5zmnlCPhCGUNZNJWOY22VM92EAhoiBYJ";

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String headerToken = null;

        String principal = (String) authentication.getPrincipal();
        if (principal != null && principal.startsWith("Bearer ")) {
            headerToken = principal.substring(7);
        }
        if(token.equals(headerToken)) {
            authentication.setAuthenticated(true);
            return authentication;
        } else {
            throw new BadCredentialsException("The API key was not found or not the expected value.");
        }
    }
}
