package com.app.horizon.screens.authentication.login;

import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.app.horizon.core.network.models.UserProfile;
import com.app.horizon.core.store.MainAppStore;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Inject;


public class LoginActivityViewModel extends ViewModel {

    MainAppStore store;
    private FirebaseFirestore firestore;

    @Inject
    public LoginActivityViewModel(MainAppStore store, FirebaseFirestore firestore) {
        this.store = store;
        this.firestore = firestore;
    }

    public void setLoggedIn(boolean loggedIn){
       store.setLoggedIn(loggedIn);
    }

    public void addUserToCloud(UserProfile userProfile) {
        firestore.collection("users")
                .document(userProfile.getUserUid())
                .set(userProfile)
                .addOnSuccessListener(aVoid -> Log.e("Success", "User Registered!"))
                .addOnFailureListener(e -> Log.e("Error!", e.getMessage()));
    }
}
