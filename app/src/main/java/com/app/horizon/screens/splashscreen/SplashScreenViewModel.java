package com.app.horizon.screens.splashscreen;

import com.app.horizon.core.store.MainAppStore;
import com.app.horizon.core.base.BaseViewModel;

import javax.inject.Inject;


public class SplashScreenViewModel extends BaseViewModel {


    public SplashScreenViewModel(MainAppStore store) {
        super(store);
    }

    public boolean isFirstTimeLaunch(){
        return getStore().isFirstTimeLaunch();
    }

    public boolean isLoggedIn(){
        return getStore().isLoggedIn();
    }

}
