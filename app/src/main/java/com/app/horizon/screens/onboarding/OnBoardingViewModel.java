package com.app.horizon.screens.onboarding;

import android.databinding.ObservableBoolean;

import com.app.horizon.core.store.MainAppStore;
import com.app.horizon.core.base.BaseViewModel;

import javax.inject.Inject;


public class OnBoardingViewModel extends BaseViewModel {

    public ObservableBoolean isLastPage;

    @Inject
    public OnBoardingViewModel(MainAppStore store){
        super(store);
        isLastPage = new ObservableBoolean();
    }

    public void setIsLastPage(boolean isLastPage){
        this.isLastPage.set(isLastPage);
    }

    public boolean isFirstTimeLaunch(){
        return getStore().isFirstTimeLaunch();
    }

    public void setFirstTimeLaunch(){
        getStore().setFirstTimeLaunch();
    }

}
