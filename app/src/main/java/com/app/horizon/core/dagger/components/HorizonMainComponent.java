package com.app.horizon.core.dagger.components;

import com.app.horizon.HorizonMainApplication;
import com.app.horizon.core.dagger.builders.ActivityBuilder;
import com.app.horizon.core.dagger.modules.app.HorizonAppModule;
import com.app.horizon.core.dagger.modules.network.GsonModule;
import com.app.horizon.core.dagger.scopes.MainAppScope;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;


@MainAppScope
@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,
                    HorizonAppModule.class,
                    ActivityBuilder.class,
                    GsonModule.class})
public interface HorizonMainComponent {

    void inject(HorizonMainApplication application);

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder horizonApplication(HorizonMainApplication application);
        HorizonMainComponent build();
    }
}