package com.jobsearchmanager.jobsearchmanager.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTManager implements Serializable {

    private static final long JWT_TOKEN_DURATION = 5 * 60 * 60;

    private final String secretKey = "testSecret";

    public String getUsername(String token){
        return this.getClaimFromToken(token, Claims::getSubject);
    }

    public String getId(String token){
        return this.getClaimFromToken(token, Claims::getId);
    }

    public Date getExpirationDateFromToken(String token){
        return this.getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims claims = this.getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(this.secretKey.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token){
        final Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String getToken(Map<String, Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_DURATION * 1000))
                .signWith(SignatureAlgorithm.HS512, this.secretKey.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return this.getToken(claims, userDetails.getUsername());
    }

    public Boolean isTokenValid(String token, UserDetails userDetails){
        final String username = this.getUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

}
