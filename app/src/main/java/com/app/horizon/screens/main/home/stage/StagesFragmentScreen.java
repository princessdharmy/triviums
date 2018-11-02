package com.app.horizon.screens.main.home.stage;

import java.io.Serializable;

import me.aartikov.alligator.Screen;

public class StagesFragmentScreen implements Screen , Serializable {
    private String categoryId;
    private String categoryName;


    public StagesFragmentScreen(String categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }


    public String getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
