package com.mealmate.backend.controller;

import com.mealmate.backend.dto.*;
import com.mealmate.backend.entity.*;
import com.mealmate.backend.repository.UserRepository;
import com.mealmate.backend.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
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
//            case ADMIN:
//                newUser = new Admin();
//                break;
            default:
                return ResponseEntity.badRequest().body("Invalid role provided.");
        }

        newUser.setName(request.getName());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole(request.getRole());

        userRepository.save(newUser);

        String accessToken = jwtService.generateToken(request.getEmail(), true);
        String refreshToken = jwtService.generateToken(request.getEmail(), false);
        JwtResponse response = new JwtResponse(accessToken, refreshToken, UserDto.fromEntity(newUser));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        String accessToken = jwtService.generateToken(request.getEmail(), true);
        String refreshToken = jwtService.generateToken(request.getEmail(), false);
        User user = userRepository.findByEmail(request.getEmail()).get();
        JwtResponse response = new JwtResponse(accessToken, refreshToken, UserDto.fromEntity(user));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
        if (jwtService.validateToken(request.refreshToken())) {
            String username = jwtService.extractUsername(request.refreshToken());
            String accessToken = jwtService.generateToken(username, true);
            String refreshToken = jwtService.generateToken(username, false);
            User user = userRepository.findByEmail(username).get();
            JwtResponse response = new JwtResponse(accessToken, refreshToken, UserDto.fromEntity(user));
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }
    }
}
