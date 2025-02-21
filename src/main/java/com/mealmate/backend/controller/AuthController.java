package com.mealmate.backend.controller;

import com.mealmate.backend.dto.JwtResponse;
import com.mealmate.backend.dto.LoginRequest;
import com.mealmate.backend.dto.RefreshTokenRequest;
import com.mealmate.backend.entity.User;
import com.mealmate.backend.repository.UserRepository;
import com.mealmate.backend.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        String accessToken = jwtService.generateToken(request.getEmail(), true);
        String refreshToken = jwtService.generateToken(request.getEmail(), false);
        User user = userRepository.findByEmail(request.getEmail()).get();
        JwtResponse response = new JwtResponse(accessToken, refreshToken, user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
        if (jwtService.validateToken(request.refreshToken())) {
            String username = jwtService.extractUsername(request.refreshToken());
            String accessToken = jwtService.generateToken(username, true);
            String refreshToken = jwtService.generateToken(username, false);
            User user = userRepository.findByEmail(username).get();
            JwtResponse response = new JwtResponse(accessToken, refreshToken, user);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }
    }
}
