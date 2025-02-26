package com.mealmate.backend.controller;

import com.mealmate.backend.dto.JwtResponse;
import com.mealmate.backend.dto.UserDto;
import com.mealmate.backend.entity.User;
import com.mealmate.backend.repository.UserRepository;
import com.mealmate.backend.service.JwtService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.Optional;

@Tag(name = "User API")
@RequiredArgsConstructor
@RepositoryRestController
public class UserController {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @GetMapping("/users/me")
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String email = jwtService.extractUsername(token);
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            return ResponseEntity.status(404).body("User not found");
        }

        String accessToken = jwtService.generateToken(email, true);
        String refreshToken = jwtService.generateToken(email, false);
        JwtResponse response = new JwtResponse(accessToken, refreshToken, UserDto.fromEntity(user.get()));
        return ResponseEntity.ok(response);
    }
}