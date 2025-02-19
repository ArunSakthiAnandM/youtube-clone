package com.arun.ytclone.config;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

public class AudienceValidator implements OAuth2TokenValidator<Jwt> {

    private final String audience;

    public AudienceValidator(String audience) {
        this.audience = audience;
    }

    @Override
    public OAuth2TokenValidatorResult validate(Jwt jwt) {
        System.out.println("Inside Audience Validator");
        if (jwt.getAudience().contains(audience)) {
            System.out.println("Success Audience Validator");
            return OAuth2TokenValidatorResult.success();
        }
        System.out.println("Failure Audience Validator");
        return OAuth2TokenValidatorResult.failure(new OAuth2Error("Invalid audience for the given token"));
    }
}
