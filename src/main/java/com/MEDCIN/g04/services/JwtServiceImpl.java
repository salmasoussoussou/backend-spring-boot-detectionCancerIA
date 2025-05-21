package com.MEDCIN.g04.services.impl;

import com.MEDCIN.g04.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {
//C’est comme un mot de passe secret que seul le serveur connaît.
    private static final String SECRET_KEY = "wH+88Hq42m8vj1X6k5oO7UGvH5b2Oq2HVw7Rvm38I0PUY86E9rI9tDnoGmwyX9V6";
    //créer un token avec juste le nom d’utilisateur (username).
//Elle appelle une autre méthode (celle en dessous) en lui passant un HashMap vide
    public String generateToken(String username) {
        return generateToken(new HashMap<>(), username);
    }

    public String generateToken(Map<String, Object> extraClaims, String username) {
        return Jwts.builder()
                .claims(extraClaims)  // Replaces setClaims()
                .subject(username)    // Replaces setSubject()
                .issuedAt(new Date(System.currentTimeMillis()))  // Replaces setIssuedAt()
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24h, replaces setExpiration()
                .signWith(getSignInKey())  // signe le token avec la clé secrète (pour qu’il soit sécurisé)
                .compact();// transforme tout ça en une chaîne de caractères (le token final)
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        //Est-ce que le nom dans le token est le même que celui de l’utilisateur ?
        //Est-ce que le token n’est pas expiré ?
        //si oui (token valide)
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    //Cette méthode extrait le nom d’utilisateur (username) du token.

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}