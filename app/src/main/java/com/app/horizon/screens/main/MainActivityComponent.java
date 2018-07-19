package com.app.horizon.screens.main;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by Ayokunle Paul on 7/19/18.
 */
@Subcomponent(modules = {MainActivityModule.class})
public interface MainActivityComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>{}

}
