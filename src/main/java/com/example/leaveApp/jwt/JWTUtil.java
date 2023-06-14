package com.example.leaveApp.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Configuration;


import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Configuration
public class JWTUtil {
   private final static String SECRET_KEY = "576D5A7134743677397A24432646294A404E635266556A586E32723575387821";
    long expirationTime = 1000*60*60*24; //(24 hours)

    public String generateToken(String userId, String userEmail) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setSubject(userEmail)
                .claim("userId",userId)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
    public String extractUserEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public String extractUserId(String token) {
        return extractClaim(token, claims -> claims.get("userId", String.class));
    }
    public Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
    public boolean isTokenExpired(String token) {
        Date expiration = extractExpirationDate(token);
        return expiration.before(new Date());
    }

    public boolean validateToken(String token, String userId) {
        String extractedUserId = extractUserId(token);
        return extractedUserId.equals(userId) && !isTokenExpired(token);
    }
}
