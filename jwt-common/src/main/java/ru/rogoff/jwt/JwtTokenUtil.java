package ru.rogoff.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtTokenUtil {

    private final Key secretKey;

    public JwtTokenUtil(String secretKey) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    /**
     * Generate a JWT token
     *
     * @param subject the subject (usually user identifier)
     * @param expirationMillis expiration time in milliseconds
     * @return the generated JWT token
     */
    public String generate(String subject, long expirationMillis) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationMillis);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Validate a JWT token
     *
     * @param token the JWT token to validate
     * @return the claims if valid
     * @throws JwtException if the token is invalid or expired
     */
    public Claims validate(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Extract the subject (user identifier) from a token
     *
     * @param token the JWT token
     * @return the subject
     */
    public String getSubject(String token) {
        Claims claims = validate(token);
        return claims.getSubject();
    }
}
