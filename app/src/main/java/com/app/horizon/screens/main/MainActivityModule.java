package com.app.horizon.screens.main;

import com.app.horizon.core.store.MainAppStore;

import dagger.Module;
import dagger.Provides;


@Module
public class MainActivityModule {

    @Provides
    MainActivityViewModel provideMainActivityViewModel(MainAppStore store){
        return new MainActivityViewModel(store);
    }
}
