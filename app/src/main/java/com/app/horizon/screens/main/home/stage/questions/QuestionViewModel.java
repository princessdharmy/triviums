package com.app.horizon.screens.main.home.stage.questions;

import android.arch.lifecycle.ViewModel;


public class QuestionViewModel extends ViewModel{

    public QuestionRepository repository;

    public QuestionViewModel(QuestionRepository repository){
        this.repository = repository;
    }
}
