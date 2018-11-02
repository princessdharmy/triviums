package com.app.horizon.screens.main;

import java.io.Serializable;

import me.aartikov.alligator.Screen;

public class HomeScreen implements Screen, Serializable {

    private String message;

    public HomeScreen(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
