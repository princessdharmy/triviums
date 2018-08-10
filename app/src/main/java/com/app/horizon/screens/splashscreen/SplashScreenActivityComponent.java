package com.app.horizon.screens.splashscreen;


import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent(modules = {SplashScreenActivityModule.class})
public interface SplashScreenActivityComponent extends AndroidInjector<SplashScreenActivity>{

        @Subcomponent.Builder
        abstract class Builder extends AndroidInjector.Builder<SplashScreenActivity>{}

}
