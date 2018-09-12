package com.app.horizon.screens.main.profile;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ProfileFragmentProvider {

    @ContributesAndroidInjector(modules = ProfileFragmentModule.class)
    abstract ProfileFragment provideProfileFragment();
}
