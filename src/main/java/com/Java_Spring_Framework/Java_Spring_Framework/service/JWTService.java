package com.Java_Spring_Framework.Java_Spring_Framework.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.Map;

@Service
public class JWTService {
    private final SecretKey secretKey;

    public JWTService() {
        try {
            SecretKey k = KeyGenerator.getInstance("HmacSHA256").generateKey();
            secretKey = Keys.hmacShaKeyFor(k.getEncoded());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getJWTToken(String username, Map<String, Object> claims){
        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*15))
                .signWith(secretKey)
                .compact();
    }

    public String getUsername(String token){
        Claims data = getTokenData(token);
        if (data == null) return null;
        return data.getSubject();
    }

    public Object getFieldFromToken(String token, String key){
        Claims data = getTokenData(token);
        if (data == null) return null;
        return data.get(key);
    }

    private Claims getTokenData(String token){
        try {
            return Jwts
                    .parser()
                    .verifyWith(secretKey).build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            return null;
        }
    }
}