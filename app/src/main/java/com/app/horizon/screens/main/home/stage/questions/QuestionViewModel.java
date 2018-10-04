package com.app.horizon.screens.main.home.stage.questions;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.app.horizon.core.store.online.question.QuestionResponse;


public class QuestionViewModel extends ViewModel{

    public QuestionRepository repository;
    private LiveData<QuestionResponse> liveData;

    public QuestionViewModel(QuestionRepository repository){
        this.repository = repository;
    }

    public LiveData<QuestionResponse> getQuestion(String categoryId, String page) {
        if(liveData == null)
            liveData = repository.fetchQuestion(categoryId, page);
        return liveData;
    }
}
