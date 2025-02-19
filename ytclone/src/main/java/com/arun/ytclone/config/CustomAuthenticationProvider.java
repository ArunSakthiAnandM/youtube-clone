package com.arun.ytclone.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("Inside Authenticate");
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        System.out.println("Inside Authenticate");
        return false;
    }
}
