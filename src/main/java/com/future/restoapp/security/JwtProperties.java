package com.future.restoapp.security;

public interface JwtProperties {
    String SECRET = "SomeSecretForJWTGeneration";
    Integer EXPIRATION_TIME = 864_000_000; // 10 days
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
