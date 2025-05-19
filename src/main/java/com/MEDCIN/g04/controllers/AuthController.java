package com.MEDCIN.g04.controllers;

import com.MEDCIN.g04.dto.AuthRequest;
import com.MEDCIN.g04.dto.JwtResponse;
import com.MEDCIN.g04.models.UserInfo;
import com.MEDCIN.g04.repositories.UserInfoRepository;
import com.MEDCIN.g04.services.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(JwtService jwtService,
                          AuthenticationManager authenticationManager,
                          UserInfoRepository userInfoRepository,
                          PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userInfoRepository = userInfoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public JwtResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authRequest.getEmail());
            // 👉 Récupérer l'utilisateur et son rôle
            UserInfo user = userInfoRepository.findByEmail(authRequest.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            // 👉 Retourner le token et le rôle
            return new JwtResponse(token, user.getRole().name());
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

    @PostMapping("/register/admin")
    public String addAdmin(@RequestBody UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfo.setRole(UserInfo.Role.ADMIN);
        userInfoRepository.save(userInfo);
        return "Admin added successfully";
    }

    @PostMapping("/register/medecin")
    public String addMedecin(@RequestBody UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfo.setRole(UserInfo.Role.MEDECIN);
        userInfoRepository.save(userInfo);
        return "Medecin added successfully";
    }
}