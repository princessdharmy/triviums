package com.app.horizon.screens.main.home.stage.questions;

import com.app.horizon.core.store.online.services.ApiService;

public class QuestionRepository {

    ApiService apiService;

    public QuestionRepository(ApiService apiService) {
        this.apiService = apiService;
    }
}
