package com.app.horizon.screens.authentication.login;

import android.arch.lifecycle.ViewModel;

import com.app.horizon.core.store.MainAppStore;

import javax.inject.Inject;


public class LoginActivityViewModel extends ViewModel {

    MainAppStore store;
    @Inject
    public LoginActivityViewModel(MainAppStore store) {
        this.store = store;
    }

    public void setLoggedIn(boolean loggedIn){
       store.setLoggedIn(loggedIn);
    }
}
