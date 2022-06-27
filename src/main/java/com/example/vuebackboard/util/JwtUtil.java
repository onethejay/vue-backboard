package com.example.vuebackboard.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    String secret;

    public String createToken(String userId, String userName) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        // Adding 30 mins using Date constructor.
        Calendar date = Calendar.getInstance();
//        System.out.println("Current Date and Time : " + date.getTime());
        long timeInSecs = date.getTimeInMillis();
        Date afterAdding30Mins = new Date(timeInSecs + (30 * 60 * 1000));
        System.out.println("After adding 30 mins : " + afterAdding30Mins);

        return JWT.create()
                .withIssuer("vue-board")
                .withClaim("userId", userId)
                .withClaim("userName", userName)
                .withIssuedAt(date.getTime())
                .withExpiresAt(afterAdding30Mins)
                .sign(algorithm);
    }

    public DecodedJWT decodeToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("vue-board")
                    .build();
            return verifier.verify(token);

        } catch (JWTVerificationException e) {
            log.error("JWTVerificationException: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return null;
    }
}