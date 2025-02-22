package com.mealmate.backend.dto;

import com.mealmate.backend.dto.UserDto;

public record JwtResponse(
        String accessToken,
        String refreshToken,
        UserDto user
) {

}
