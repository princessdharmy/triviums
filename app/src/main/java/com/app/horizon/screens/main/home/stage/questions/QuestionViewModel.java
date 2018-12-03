package com.app.horizon.screens.main.home.stage.questions;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.app.horizon.core.store.online.question.QuestionResponse;
import com.app.horizon.core.store.online.question.QuestionResultsResponse;

import java.util.ArrayList;


public class QuestionViewModel extends ViewModel{

    public QuestionRepository repository;
    private LiveData<QuestionResultsResponse> liveData;

    public QuestionViewModel(QuestionRepository repository){
        this.repository = repository;
    }

    public LiveData<QuestionResultsResponse> getQuestion(String categoryId, String page) {
        if(liveData == null)
            liveData = repository.fetchQuestion(categoryId, page);
        return liveData;
    }

    public void saveProgress(String categoryName, String stageNumber, int score, int totalPages){
        repository.saveQuizProgress(categoryName, stageNumber, score, totalPages);
    }
}
