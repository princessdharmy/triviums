package com.app.horizon.core.dagger.components;

import com.app.horizon.HorizonMainApplication;
import com.app.horizon.core.dagger.builders.ActivityBuilder;
import com.app.horizon.core.dagger.modules.FirebaseModule;
import com.app.horizon.core.dagger.modules.HorizonAppModule;
import com.app.horizon.core.dagger.scopes.MainAppScope;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;


@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,
                    HorizonAppModule.class,
                    FirebaseModule.class,
                    ActivityBuilder.class})
public interface HorizonMainComponent extends AndroidInjector<HorizonMainApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<HorizonMainApplication> {}

}