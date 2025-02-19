package com.arun.ytclone.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer;

    @Value("${auth0.audience}")
    private String audience;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        try {
            System.out.println("Inside Filter Chain");
            http
                    .authorizeHttpRequests(authoriseRequests ->
                            authoriseRequests.anyRequest().authenticated())
                    .sessionManagement(customise ->
                            customise.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .cors(Customizer.withDefaults())
                    .csrf(AbstractHttpConfigurer::disable)
                    .oauth2ResourceServer(oauth2 ->
                            oauth2.jwt(Customizer.withDefaults()))
                    .exceptionHandling(exception ->
                            exception
                                    .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                                    .accessDeniedHandler(new BearerTokenAccessDeniedHandler()));

        } catch (Exception e) {
            System.out.println("ERROR - Inside Filter Chain");
            throw new RuntimeException(e);
        }

        return http.build();
    }

    @Bean
    @Primary
    public JwtDecoder jwtDecoder() {
        System.out.println("Inside JWT Decoder");
//        String jwkSetUri = "https://dev-8f2841v46pgbmzmn.us.auth0.com/.well-known/jwks.json";
//        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
        NimbusJwtDecoder jwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuer);

        OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(audience);
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);

        jwtDecoder.setJwtValidator(withAudience);
        System.out.println("JWT Decoder" + jwtDecoder.toString());
        return  jwtDecoder;
    }

}
