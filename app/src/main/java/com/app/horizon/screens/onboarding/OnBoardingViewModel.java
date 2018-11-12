package com.app.horizon.screens.onboarding;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import com.app.horizon.core.store.MainAppStore;

import javax.inject.Inject;


public class OnBoardingViewModel extends ViewModel {

    public ObservableBoolean isLastPage;
    private MainAppStore store;

    @Inject
    public OnBoardingViewModel(MainAppStore store){
        isLastPage = new ObservableBoolean();
        this.store = store;
    }

    public void setIsLastPage(boolean isLastPage){
        this.isLastPage.set(isLastPage);
    }

    public boolean isFirstTimeLaunch(){
        return store.isFirstTimeLaunch();
    }

    public void setFirstTimeLaunch(){
        store.setFirstTimeLaunch();
    }

}
