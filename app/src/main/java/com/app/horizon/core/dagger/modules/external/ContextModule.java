package com.app.horizon.core.dagger.modules.external;

import android.content.Context;

import com.app.horizon.core.dagger.scopes.MainAppScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ayokunle Paul on 7/3/18.
 */

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @MainAppScope
    @Provides
    public Context provideContext(){
        return context;
    }
}
