package com.app.horizon.screens.authentication.login;

import com.app.horizon.core.store.MainAppStore;
import com.app.horizon.core.view.BaseViewModel;

import javax.inject.Inject;


public class LoginActivityViewModel extends BaseViewModel {

    private MainAppStore store;

    @Inject
    public LoginActivityViewModel(MainAppStore store) {
        this.store = store;
    }

    public void setLoggedIn(boolean loggedIn){
        store.setLoggedIn(loggedIn);
    }
}
