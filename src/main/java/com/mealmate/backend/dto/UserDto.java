package com.mealmate.backend.dto;

import com.mealmate.backend.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDto {
    private UUID id;
    private String name;
    private String email;
    private String role;

    public UserDto(UUID id, String name, String email, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public static UserDto fromEntity(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getRole().name());
    }
}
