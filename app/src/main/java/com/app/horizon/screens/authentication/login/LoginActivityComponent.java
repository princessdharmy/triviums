package com.app.horizon.screens.authentication.login;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = {LoginActivityModule.class})
public interface LoginActivityComponent extends AndroidInjector<LoginActivity>{

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<LoginActivity>{}

}
