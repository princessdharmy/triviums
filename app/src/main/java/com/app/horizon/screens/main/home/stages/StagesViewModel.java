package com.app.horizon.screens.main.home.stages;


import android.arch.lifecycle.LiveData;

import com.app.horizon.core.base.BaseViewModel;
import com.app.horizon.core.store.MainAppStore;
import com.app.horizon.core.store.offline.entities.question.QuestionResponse;

import javax.inject.Inject;

public class StagesViewModel extends BaseViewModel<StagesNavigator> {


    private LiveData<QuestionResponse> responseLiveData;
    @Inject
    StageRepository stageRepository;

    public StagesViewModel(MainAppStore store) {
        super(store);
    }

    public void onNavBackClick(){
        getNavigator().goBack();
    }

    LiveData<QuestionResponse> getStage(String categoryId){
        if(responseLiveData == null)
            responseLiveData = stageRepository.fetchStages(categoryId);
        return responseLiveData;
    }

}
