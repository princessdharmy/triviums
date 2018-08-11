package com.app.horizon.core.dagger.modules.app;

import android.content.Context;

import com.app.horizon.HorizonMainApplication;
import com.app.horizon.core.dagger.scopes.MainAppScope;
import com.app.horizon.core.store.MainAppStore;
import com.app.horizon.core.store.offline.OfflineStore;
import com.app.horizon.core.store.online.OnlineStore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class HorizonAppModule {

    @MainAppScope
    @Provides
    Context provideContext(HorizonMainApplication application){
        return application;
    }

    @Provides
    @Singleton
    MainAppStore provideMainAppStore(OnlineStore onlineStore, OfflineStore offlineStore){
        return new MainAppStore(onlineStore, offlineStore);
    }
}
