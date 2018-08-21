package com.app.horizon.screens.main.home.stage.stages;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.app.horizon.core.store.offline.entities.question.QuestionResponse;

public class StagesViewModel extends ViewModel {

    public StageRepository stageRepository;
    private LiveData<QuestionResponse> responseLiveData;


    public StagesViewModel(StageRepository repository) {
        this.stageRepository = repository;
    }


    LiveData<QuestionResponse> getStage(String categoryId){
        if(responseLiveData == null)
            responseLiveData = stageRepository.fetchStages(categoryId);
        return responseLiveData;
    }

}
