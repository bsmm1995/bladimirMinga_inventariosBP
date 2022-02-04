package com.example.inventariobp.utils.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    private final String KEY = "BSMM-1234";

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 1000 * 20))
                .signWith(SignatureAlgorithm.HS256, KEY).compact();
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 1000 * 30))
                .signWith(SignatureAlgorithm.HS256, KEY).compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        return tokenNotExpired(token) && userDetails.getUsername().equals(getUserFromToken(token));
    }

    public boolean tokenNotExpired(String token) {
        return new Date().before(getClaims(token).getExpiration());
    }

    public String getUserFromToken(String token) {
        return getClaims(token).getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }
}
