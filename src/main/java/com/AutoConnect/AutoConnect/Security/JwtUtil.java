package com.AutoConnect.AutoConnect.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET = "MaCle";
    private final long EXPIRATION = 1000 * 60 * 60 * 10;

    private final Algorithm algorithm = Algorithm.HMAC256(SECRET);

    public String generateToken(String email, String role) {
        return JWT.create()
                .withSubject(email)
                .withClaim("role", role)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION))
                .sign(algorithm);
    }


    public String extractEmail(String token) {
        return JWT.require(algorithm)
                .build()
                .verify(token)
                .getSubject();
    }
    public String extractRole(String token){
        return JWT.require(algorithm)
                .build()
                .verify(token)
                .getClaim("role")
                .asString();
    }
    public boolean validateToken(String token , String email) {
        try {
            String subject = extractEmail(token);
            return subject.equals(email);
        } catch (JWTVerificationException e) {
            return false;
        }
    }

}
