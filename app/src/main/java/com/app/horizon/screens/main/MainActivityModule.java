package com.app.horizon.screens.main;


import dagger.Module;
import dagger.Provides;


@Module
public class MainActivityModule {

    @Provides
    MainActivityViewModel provideMainActivityViewModel(){
        return new MainActivityViewModel();
    }


}
