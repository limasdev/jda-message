package com.github.cadindev.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class User {
    private final UUID uniqueId;
    private final String message;

    public static User getUser(UUID uniqueId, String message) {
        return new User(uniqueId, message);
    }
}
