package com.app.horizon.core.dagger.components;

import com.app.horizon.core.dagger.modules.database.HorizonDatabaseModule;
import com.app.horizon.core.dagger.modules.external.ContextModule;
import com.app.horizon.core.dagger.scopes.MainAppScope;
import com.app.horizon.screens.onboarding.OnBoardingActivity;

import dagger.Component;


@MainAppScope
@Component(modules = {HorizonDatabaseModule.class, ContextModule.class})
public interface HorizonMainComponent {
    void inject(OnBoardingActivity activity);
}