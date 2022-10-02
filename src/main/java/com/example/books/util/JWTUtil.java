package com.example.books.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JWTUtil {
    /**
     * 获取token
     * @param name
     * @param key
     * @return token
     */
    public static String getToken(String name,String key) {
        Date date = new Date(System.currentTimeMillis() + 10*60*1000);
        Algorithm algorithm = Algorithm.HMAC256(key);
        return JWT.create()
                .withClaim("name", name)
                .withSubject("info")
                .withAudience(name)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    /**
     * 验证token
     */
    public static boolean verify(String token, String name, String secret) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .withClaim("name", name)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return true;
    }
    public static String getName(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("name").asString();
    }


}
