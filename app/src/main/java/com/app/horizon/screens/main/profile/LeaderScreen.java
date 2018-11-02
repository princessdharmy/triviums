package com.app.horizon.screens.main.profile;

import java.io.Serializable;

import me.aartikov.alligator.Screen;

public class LeaderScreen implements  Screen, Serializable {

    private String message;

    public LeaderScreen(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }
}
