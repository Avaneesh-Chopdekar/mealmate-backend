package com.mealmate.backend.controller;

import com.mealmate.backend.dto.*;
import com.mealmate.backend.entity.*;
import com.mealmate.backend.repository.UserRepository;
import com.mealmate.backend.service.JwtService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@Tag(name = "Authentication API")
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        try {
            Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
            if (existingUser.isPresent()) {
                return ResponseEntity.badRequest().body("Email is already in use.");
            }

            User newUser;
            switch (request.getRole()) {
                case CONSUMER:
                    newUser = new Consumer();
                    break;
                case RIDER:
                    newUser = new Rider();
                    break;
                case RESTAURANT:
                    newUser = new Restaurant();
                    break;
                case ADMIN:
                    newUser = new Admin();
                    break;
                default:
                    return ResponseEntity.badRequest().body("Invalid role provided.");
            }

            String accessToken = jwtService.generateToken(request.getEmail(), true);
            String refreshToken = jwtService.generateToken(request.getEmail(), false);

            newUser.setName(request.getName());
            newUser.setEmail(request.getEmail());
            newUser.setPassword(passwordEncoder.encode(request.getPassword()));
            newUser.setRole(request.getRole());
            newUser.setRefreshToken(refreshToken);

            userRepository.save(newUser);

            JwtResponse response = new JwtResponse(accessToken, refreshToken, UserDto.fromEntity(newUser));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        String accessToken = jwtService.generateToken(request.getEmail(), true);
        String refreshToken = jwtService.generateToken(request.getEmail(), false);
        User user = userRepository.findByEmail(request.getEmail()).get();
        user.setRefreshToken(refreshToken);
        JwtResponse response = new JwtResponse(accessToken, refreshToken, UserDto.fromEntity(user));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
        if (!jwtService.validateToken(request.refreshToken())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }

        String username = jwtService.extractUsername(request.refreshToken());
        User user = userRepository.findByEmail(username).get();

        if (user.getRefreshToken() == null || !user.getRefreshToken().equals(request.refreshToken())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }

        String accessToken = jwtService.generateToken(username, true);
        String refreshToken = jwtService.generateToken(username, false);
        user.setRefreshToken(refreshToken);
        JwtResponse response = new JwtResponse(accessToken, refreshToken, UserDto.fromEntity(user));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody RefreshTokenRequest request) {
        if (!jwtService.validateToken(request.refreshToken())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }
        String email = jwtService.extractUsername(request.refreshToken());

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        user.setRefreshToken(null);
        userRepository.save(user);

        HashMap<String, String> response = new HashMap<>();
        response.put("message", "Logged out successfully");

        return ResponseEntity.ok(response);
    }
}
