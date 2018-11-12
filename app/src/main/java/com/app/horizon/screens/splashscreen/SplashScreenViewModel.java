package com.app.horizon.screens.splashscreen;

import android.arch.lifecycle.ViewModel;

import com.app.horizon.core.store.MainAppStore;

import javax.inject.Inject;


public class SplashScreenViewModel extends ViewModel {

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
