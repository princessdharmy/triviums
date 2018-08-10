package com.app.horizon.screens.authentication.login;

import com.app.horizon.core.store.MainAppStore;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginActivityModule {

    @Provides
    LoginActivityViewModel providesLoginActivityViewModel(MainAppStore store){
        return new LoginActivityViewModel(store);
    }
}
