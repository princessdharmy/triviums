package com.app.horizon.screens.authentication.login;

import android.arch.lifecycle.ViewModelProvider;

import com.app.horizon.core.store.MainAppStore;
import com.app.horizon.utils.ViewModelProviderFactory;
import com.google.firebase.firestore.FirebaseFirestore;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginActivityModule {

    @Provides
    LoginActivityViewModel providesLoginActivityViewModel(MainAppStore store,
                                                          FirebaseFirestore firebaseFirestore){
        return new LoginActivityViewModel(store, firebaseFirestore);
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(LoginActivityViewModel viewModel){
        return new ViewModelProviderFactory<>(viewModel);
    }


}
