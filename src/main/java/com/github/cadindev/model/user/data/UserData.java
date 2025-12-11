package com.github.cadindev.model.user.data;

import com.github.cadindev.model.user.User;

import java.util.HashMap;

public class UserData extends HashMap<User, String> {
    public UserData() {
    }

    public void getMessage(User user) {
        this.get(user.getMessage());
    }
}
