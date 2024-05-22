package com.example.demo.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.model.request.LoginDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class JWTUtil {
    private static final long AUTH_TIME = 7*24*60*60;
    private static Algorithm ALGORITHM;

    @Value("${JWT_SECRET}")
    public void setAlgorithm(String secretKey){
        ALGORITHM = Algorithm.HMAC256(secretKey);
    }

    public static String makeAuthToken(LoginDto loginDto){
        return JWT.create()
                .withSubject(String.valueOf(loginDto.getEmail()))
                .withClaim("exp", Instant.now().getEpochSecond()+AUTH_TIME)
                .sign(ALGORITHM);
    }

    public static VerifyResult verify(String token){
        try {
            DecodedJWT verify = JWT
                    .require(ALGORITHM)
                    .build()
                    .verify(token);

            return VerifyResult.builder()
                    .success(true)
                    .id(verify.getSubject())
                    .build();

        } catch (Exception ex){
            DecodedJWT decode = JWT.decode(token);
            return VerifyResult.builder()
                    .success(false)
                    .id(decode.getSubject())
                    .build();
        }
    }
}
