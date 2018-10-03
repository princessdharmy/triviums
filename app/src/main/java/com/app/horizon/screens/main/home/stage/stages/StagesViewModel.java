package com.app.horizon.screens.main.home.stage.stages;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.view.View;

import com.app.horizon.core.store.online.question.QuestionResponse;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.Map;


public class StagesViewModel extends ViewModel {

    public StageRepository stageRepository;
    private LiveData<QuestionResponse> responseLiveData;
    private LiveData<Map<String, Object>> liveData;


    public StagesViewModel(StageRepository repository) {
        this.stageRepository = repository;
    }


    LiveData<QuestionResponse> getStage(String categoryId){
        if(responseLiveData == null)
            responseLiveData = stageRepository.fetchStages(categoryId);
        return responseLiveData;
    }

    public LiveData<Map<String, Object>> getProgressDetails(String categoryName) {
        if(liveData == null)
            liveData = stageRepository.getProgress(categoryName);
        return liveData;
    }

}
