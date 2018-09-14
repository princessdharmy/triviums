package com.app.horizon.screens.authentication.login;

import android.arch.lifecycle.ViewModel;

import com.app.horizon.core.store.MainAppStore;
import com.app.horizon.core.store.online.services.FirestoreService;

import javax.inject.Inject;


public class LoginActivityViewModel extends ViewModel {

    MainAppStore store;
    FirestoreService firestoreService;

    @Inject
    public LoginActivityViewModel(MainAppStore store, FirestoreService firestoreService) {
        this.store = store;
        this.firestoreService = firestoreService;
    }

    public void setLoggedIn(boolean loggedIn){
       store.setLoggedIn(loggedIn);
    }

    public void addNewUser(String uId, String name, String email, String profilePic){
        firestoreService.addNewContact(uId, name, email, profilePic);
    }
}
