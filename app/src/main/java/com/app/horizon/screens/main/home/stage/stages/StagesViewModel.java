package com.app.horizon.screens.main.home.stage.stages;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.app.horizon.core.store.online.question.FirestoreResultResponse;
import com.app.horizon.core.store.online.question.QuestionResponse;
import com.app.horizon.core.store.online.question.QuestionResultsResponse;

import java.util.Map;


public class StagesViewModel extends ViewModel {

    public StageRepository stageRepository;
    private LiveData<QuestionResultsResponse> responseLiveData;
    private LiveData<FirestoreResultResponse> liveData;


    public StagesViewModel(StageRepository repository) {
        this.stageRepository = repository;
    }


    LiveData<QuestionResultsResponse> getStage(String categoryId){
        if(responseLiveData == null)
            responseLiveData = stageRepository.fetchStages(categoryId);
        return responseLiveData;
    }

    public LiveData<FirestoreResultResponse> getProgressDetails(String categoryName) {
        if(liveData == null)
            liveData = stageRepository.getProgress(categoryName);
        return liveData;
    }

}
