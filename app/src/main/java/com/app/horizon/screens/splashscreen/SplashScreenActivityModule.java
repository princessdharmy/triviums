package com.app.horizon.screens.splashscreen;

import com.app.horizon.core.store.MainAppStore;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashScreenActivityModule {

    @Provides
    SplashScreenViewModel providesSplashScreenViewModel(MainAppStore store){
        return new SplashScreenViewModel(store);
    }
}
