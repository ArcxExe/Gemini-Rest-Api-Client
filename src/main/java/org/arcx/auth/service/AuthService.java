package org.arcx.auth.service;

import org.arcx.auth.config.JwtConfig;
import org.arcx.auth.dto.AuthRequest;
import org.arcx.auth.dto.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    private final JwtConfig jwtConfig;
    private final JwtService jwtService;

    public AuthService(JwtConfig jwtConfig, JwtService jwtService) {
        this.jwtConfig = jwtConfig;
        this.jwtService = jwtService;
    }

    public AuthResponse login(AuthRequest request){
        log.info("Logging users: {}" , request.login());

        if (jwtConfig.getAuthLogin().equals(request.login()) && jwtConfig.getAuthPassword().equals(request.password())){
            String token = jwtService.generateToken(request.login());
            log.info("Auth success");

            return new AuthResponse(token);
        }

        log.warn("No correct login or password");
        throw new RuntimeException("No correct login or password");
    }
}
