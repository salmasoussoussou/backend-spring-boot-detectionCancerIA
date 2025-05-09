package com.MEDCIN.g04.services;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(String username);
    String extractUsername(String token);
    boolean validateToken(String token, UserDetails userDetails); // Updated method signature
}