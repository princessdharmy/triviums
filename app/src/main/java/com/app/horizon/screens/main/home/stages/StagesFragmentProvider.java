package com.app.horizon.screens.main.home.stages;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class StagesFragmentProvider {

    @ContributesAndroidInjector(modules = StagesFragmentModule.class)
    abstract StagesFragment provideStagesFragmentFactory();
}
