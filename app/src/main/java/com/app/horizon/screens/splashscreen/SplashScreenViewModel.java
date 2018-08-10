package com.app.horizon.screens.splashscreen;

import com.app.horizon.core.store.MainAppStore;
import com.app.horizon.core.view.BaseViewModel;

import javax.inject.Inject;


public class SplashScreenViewModel extends BaseViewModel {

    private MainAppStore store;

    @Inject
    public SplashScreenViewModel(MainAppStore store) {
        this.store = store;
    }

    public boolean isFirstTimeLaunch(){
        return store.isFirstTimeLaunch();
    }

    public boolean isLoggedIn(){
        return store.isLoggedIn();
    }

}
