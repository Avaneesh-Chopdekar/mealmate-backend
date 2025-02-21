package com.mealmate.backend.repository.event_handler;

import com.mealmate.backend.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@RepositoryEventHandler(User.class)
public class UserEventHandler {

    private final PasswordEncoder passwordEncoder;

    @HandleBeforeCreate
    public void encryptPassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
