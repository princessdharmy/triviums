package com.app.horizon.screens.authentication.login;

import com.app.horizon.core.store.MainAppStore;
import com.app.horizon.core.base.BaseViewModel;

import javax.inject.Inject;


public class LoginActivityViewModel extends BaseViewModel {

    @Inject
    public LoginActivityViewModel(MainAppStore store) {
        super(store);
    }

    public void setLoggedIn(boolean loggedIn){
        getStore().setLoggedIn(loggedIn);
    }
}
