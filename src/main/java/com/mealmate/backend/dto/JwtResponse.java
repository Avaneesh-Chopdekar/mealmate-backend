package com.mealmate.backend.dto;

import com.mealmate.backend.entity.User;

public record JwtResponse(
        String accessToken,
        String refreshToken,
        User user
) {

}
