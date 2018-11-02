package com.app.horizon.screens.main.profile;

import java.io.Serializable;

import me.aartikov.alligator.Screen;

public class ProfileScreen implements Screen, Serializable {


    private String message;

    public ProfileScreen(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }
}
