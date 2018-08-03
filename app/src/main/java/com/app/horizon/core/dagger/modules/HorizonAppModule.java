package com.app.horizon.core.dagger.modules;

import android.content.Context;

import com.app.horizon.HorizonMainApplication;
import com.app.horizon.core.dagger.scopes.MainAppScope;
import com.app.horizon.screens.main.MainActivityComponent;
import com.app.horizon.screens.onboarding.OnBoardingActivityComponent;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ayokunle Paul on 7/19/18.
 */
@Module(subcomponents = {
        OnBoardingActivityComponent.class, MainActivityComponent.class})
public class HorizonAppModule {

    @MainAppScope
    @Provides
    Context provideContext(HorizonMainApplication application){
        return application;
    }

}
