package com.jcode_development.magalums.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.jcode_development.magalums.model.user.User;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtTokenProvide {

    @Value("${spring.security.jwt.token.secret-key}")
    private String secretKey;


    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        try {
            return JWT.create()
                    .withIssuer("magalums")
                    .withSubject(user.getLogin())
                    .withExpiresAt(getIssuedAt())
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new JwtException(exception.getMessage());
        }
    }

    public String validateJwtToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        try {
            return JWT.require(algorithm)
                    .withIssuer("magalums")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException(e);
        }
    }

    private Instant getIssuedAt() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String resolveToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}
