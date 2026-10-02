package org.arcx.auth.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class JwtConfig {

    private final long expirationMs;
    private final String jwtSecret;
    private final String authLogin;
    private final String authPassword;

    public JwtConfig(@Value("${jwt.expiration-ms}") long expirationMs,
                     @Value("${jwt.secret}") String jwtSecret,
                     @Value("${auth.login}") String authLogin,
                     @Value("${auth.password}") String authPassword) {
        this.expirationMs = expirationMs;
        this.jwtSecret = jwtSecret;
        this.authLogin = authLogin;
        this.authPassword = authPassword;
    }
}
