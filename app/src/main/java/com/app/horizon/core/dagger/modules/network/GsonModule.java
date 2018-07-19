package com.app.horizon.core.dagger.modules.network;

import com.app.horizon.core.dagger.scopes.MainAppScope;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ayokunle Paul on 7/19/18.
 */
@Module
public class GsonModule {

    @MainAppScope
    @Provides
    public Gson provideGson(){
        return new Gson();
    }

}
