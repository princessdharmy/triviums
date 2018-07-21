package com.app.horizon.screens.onboarding;

import android.databinding.ObservableBoolean;

import com.app.horizon.core.store.MainAppStore;
import com.app.horizon.core.view.BaseViewModel;

import javax.inject.Inject;

/**
 * Created by Ayokunle Paul on 7/19/18.
 */
public class OnBoardingViewModel extends BaseViewModel {

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
